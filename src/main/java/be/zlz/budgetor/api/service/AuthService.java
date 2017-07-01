package be.zlz.budgetor.api.service;

import be.zlz.budgetor.api.domain.User;
import be.zlz.budgetor.api.dto.ExceptionWrapper;
import be.zlz.budgetor.api.dto.LoginDTO;
import be.zlz.budgetor.api.dto.UserDTO;
import be.zlz.budgetor.api.exceptions.BadCredentialsException;
import be.zlz.budgetor.api.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Period;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

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
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    //fixme test for timing attack
    public String loginUser(LoginDTO loginDTO) {
        String token = "";
        User u = userRepository.findUserByEmailAddress(loginDTO.getEmail());

        if (u != null && BCrypt.checkpw(loginDTO.getPassword(), u.getPasswordHash())) {
            token = createJwt(u);
            logger.info("Logging in " + loginDTO.getEmail());
        } else {
            throw new BadCredentialsException("Wrong username or password");
        }
        return token;
    }

    public String registerUser(UserDTO user) {

        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmailAddress());
        newUser.setPasswordHash(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        userRepository.save(newUser);

        String token = createJwt(newUser);

        JmsTemplate jmsTemplate = beanFactory.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend("mail", "HELLO WORLD.");
        return token;
    }

    public String refreshJwt(String token) {
        Date expiration = JWT.decode(token).getExpiresAt();
        long userId = Long.getLong(JWT.decode(token).getClaim("id").asString());

        LocalDateTime ldt = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();

        if(ldt.isAfter(now)){
            return token;
        }
        else if(now.plus(30, ChronoUnit.MINUTES).isAfter(ldt)){
            return createJwt(userRepository.findOne(userId));
        }
        else {
            throw new BadCredentialsException("Your token has expired, please log in again");
        }
    }

    private String createJwt(User u) {
        //make jwt
        String token;
        try {
            token = JWT.create()
                    .withIssuer("budgetor")
                    .withClaim("id", Long.toString(u.getId()))
                    .withClaim("email", u.getEmailAddress())
                    .withClaim("firstName", u.getFirstName())
                    .withClaim("lastName", u.getLastName())
                    .withExpiresAt(Date.from(
                            LocalDateTime.now()
                                    .plus(1, ChronoUnit.DAYS)
                                    .atZone(ZoneId.systemDefault())
                                    .toInstant()))
                    .sign(Algorithm.HMAC256(jwtSecret));
        } catch (JWTCreationException | UnsupportedEncodingException e) {
            logger.error("Error creating JWT", e);
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
