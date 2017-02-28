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
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;

/**
 * Created by Frederik on 28/02/2017.
 */
public class AuthService {

    private final UserRepository userRepository;

    private ObjectMapper mapper;

    private final Logger logger;

    private static final String JWT_SECRET = "secret"; //todo move to config

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ObjectMapper();
        this.logger = Logger.getLogger(this.getClass());
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
        return token;
    }

    @SuppressWarnings(value = "all") //message is always the same, for now
    private String createExceptionJSON(String mess, int code) {
        try {
            return mapper.writeValueAsString(new ExceptionWrapper(mess, code));
        }
        catch (JsonProcessingException jpe) {
            logger.error("Error while creating JSON for Exception:", jpe);
            return "";
        }
    }
}
