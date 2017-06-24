package be.zlz.budgetor.api.Service;

import be.zlz.budgetor.api.domain.User;
import be.zlz.budgetor.api.repository.UserRepository;
import com.auth0.jwt.interfaces.Claim;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Frederik on 24/06/2017.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private Logger logger;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        logger = Logger.getLogger(UserService.class);
    }

    public User getCurrentUser(){
        String email = ((Map<String, Claim>)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get("email").asString();
        logger.debug("Getting info for current user with email " + email);

        if (email != null) {
            return userRepository.findUserByEmailAddress(email);
        }
        return null;
    }
}
