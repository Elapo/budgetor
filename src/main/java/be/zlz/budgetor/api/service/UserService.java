package be.zlz.budgetor.api.service;

import be.zlz.budgetor.api.domain.User;
import be.zlz.budgetor.api.dto.LoginDTO;
import be.zlz.budgetor.api.dto.PasswordDTO;
import be.zlz.budgetor.api.dto.UserDTO;
import be.zlz.budgetor.api.repository.UserRepository;
import com.auth0.jwt.interfaces.Claim;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    private Logger logger;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        logger = Logger.getLogger(UserService.class);
    }

    public User getCurrentUser() {
        String email = ((Map<String, Claim>) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get("email").asString();
        logger.debug("Getting info for current user with email " + email);

        if (email != null) {
            return userRepository.findUserByEmailAddress(email);
        }
        return null;
    }

    public User updateUser(UserDTO updated) {
        User current = getCurrentUser();

        if (current != null) {
            current.setEmailAddress(updated.getEmailAddress());
            current.setFirstName(updated.getFirstName());
            current.setLastName(updated.getLastName());
            userRepository.save(current);
        }

        return current;
    }

    public LoginDTO changePassword(PasswordDTO passwordDTO) {
        User u = getCurrentUser();

        if (u != null && BCrypt.checkpw(passwordDTO.getCurrentPass(), u.getPasswordHash())) {
            u.setPasswordHash(BCrypt.hashpw(passwordDTO.getNewPass(), BCrypt.gensalt()));
            userRepository.save(u);

            return new LoginDTO(u.getEmailAddress(), passwordDTO.getNewPass());
        }
        return new LoginDTO();
    }
}
