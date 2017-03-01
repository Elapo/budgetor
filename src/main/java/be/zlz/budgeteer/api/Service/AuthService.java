package be.zlz.budgeteer.api.Service;

import be.zlz.budgeteer.api.domain.User;
import be.zlz.budgeteer.api.repository.UserRepository;
import be.zlz.budgeteer.api.wrapper.ExceptionWrapper;
import be.zlz.budgeteer.api.wrapper.LoginWrapper;
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
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class AuthService implements BeanFactoryAware {

    private final UserRepository userRepository;

    private ObjectMapper mapper;

    private final Logger logger;

    private BeanFactory beanFactory;

    private static final String JWT_SECRET = "secret"; //todo move to config

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ObjectMapper();
        this.logger = Logger.getLogger(this.getClass());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public String LoginUser(LoginWrapper loginWrapper) {
        return "";
    }

    public String RegisterUser(User user) {
        user.setPasswordHash(BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt()));
        userRepository.save(user);
        //make jwt
        String token;
        try {
            token = JWT.create()
                    .withIssuer("budgeteer")
                    .withClaim("email", user.getEmailAddress())
                    .withClaim("firstName", user.getFirstName())
                    .withClaim("lastName", user.getLastName())
                    .sign(Algorithm.HMAC256(JWT_SECRET));
        } catch (JWTCreationException | UnsupportedEncodingException e) {
            logger.error("Error creating JWT", e);
            return createExceptionJSON("Error creating JWT", -1);
        }
        JmsTemplate jmsTemplate = beanFactory.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend("mail", "HELLO WORLD.");
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
