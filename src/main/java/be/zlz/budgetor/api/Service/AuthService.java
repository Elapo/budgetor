package be.zlz.budgetor.api.Service;

import be.zlz.budgetor.api.DTO.ExceptionWrapper;
import be.zlz.budgetor.api.DTO.LoginDTO;
import be.zlz.budgetor.api.DTO.UserDTO;
import be.zlz.budgetor.api.domain.User;
import be.zlz.budgetor.api.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.io.UnsupportedEncodingException;

@Service
public class AuthService implements BeanFactoryAware {

    private final UserRepository userRepository;

    private ObjectMapper mapper;

    private Logger logger;

    private BeanFactory beanFactory;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ObjectMapper();
        logger = Logger.getLogger(AuthService.class);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public String LoginUser(LoginDTO loginDTO) {
        String token = "";
        User u = userRepository.findUserByEmailAddress(loginDTO.getEmail());

        if (u != null && BCrypt.checkpw(loginDTO.getPassword(), u.getPasswordHash())) {
            token = createJwt(u);
            logger.info("Logging in " + loginDTO.getEmail());
        } else {
            token = createExceptionJSON("Wrong username or password", -1);
        }
        return token;
    }

    public String RegisterUser(UserDTO user) {

        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmailAddress());
        newUser.setPasswordHash(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        try {
            userRepository.save(newUser);
        } catch (ConstraintViolationException cve) {
            logger.error("Validation failed", cve);
            return createExceptionJSON("Please check if everything is filled in correctly.", -1);
        } catch (Exception sqle) {
            logger.error("User save failed!", sqle);
            return createExceptionJSON("A user with this email address already exists", -1);
        }

        String token = createJwt(newUser);

        JmsTemplate jmsTemplate = beanFactory.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend("mail", "HELLO WORLD.");
        return token;
    }

    private String createJwt(User u) {
        //make jwt
        String token;
        System.out.println("secret: " + jwtSecret);
        try {
            token = JWT.create()
                    .withIssuer("budgetor")
                    .withClaim("email", u.getEmailAddress())
                    .withClaim("firstName", u.getFirstName())
                    .withClaim("lastName", u.getLastName())
                    .sign(Algorithm.HMAC256(jwtSecret));
        } catch (JWTCreationException | UnsupportedEncodingException e) {
            //logger.error("Error creating JWT", e);
            return createExceptionJSON("Error creating JWT", -1);
        }
        return token;
    }

    @SuppressWarnings(value = "all") //message is always the same, for now
    private String createExceptionJSON(String mess, int code) {
        try {
            return mapper.writeValueAsString(new ExceptionWrapper(mess, code));
        } catch (JsonProcessingException jpe) {
            logger.error("Error while creating JSON for Exception:", jpe);
            return "";
        }
    }
}
