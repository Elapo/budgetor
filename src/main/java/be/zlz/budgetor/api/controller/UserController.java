package be.zlz.budgetor.api.controller;

import be.zlz.budgetor.api.DTO.UserDTO;
import be.zlz.budgetor.api.Service.UserService;
import be.zlz.budgetor.api.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String testThisApp() {
        return "Hello, World!";
    }

    @GetMapping("/current")
    public UserDTO getCurrentUser(){
        User current = userService.getCurrentUser();
        return new UserDTO(current);
    }
}
