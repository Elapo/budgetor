package be.zlz.budgetor.api.controller;

import be.zlz.budgetor.api.domain.User;
import be.zlz.budgetor.api.dto.PasswordDTO;
import be.zlz.budgetor.api.dto.UserDTO;
import be.zlz.budgetor.api.service.AuthService;
import be.zlz.budgetor.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    private AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String testThisApp() {
        return "Hello, World!";
    }

    @GetMapping("/current")
    public UserDTO getCurrentUser() {
        User current = userService.getCurrentUser();
        if (current != null){
            return new UserDTO(current);
        }
        throw new AccessDeniedException("You are not allowed to access this resource. Please log in first.");
    }

    @PutMapping("/current")
    public UserDTO updateCurrentUser(@RequestBody UserDTO updated) {
        return new UserDTO(userService.updateUser(updated));
    }

    @PutMapping("/current/changepassword")
    public String changePassword(@RequestBody PasswordDTO passwordDTO) {
        return authService.loginUser(userService.changePassword(passwordDTO));
    }

}
