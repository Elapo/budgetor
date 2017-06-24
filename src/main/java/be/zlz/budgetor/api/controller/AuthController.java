package be.zlz.budgetor.api.controller;

import be.zlz.budgetor.api.DTO.LoginDTO;
import be.zlz.budgetor.api.DTO.UserDTO;
import be.zlz.budgetor.api.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            headers = {"content-type=application/json"})
    public String registerUser(@RequestBody UserDTO user) {
        return authService.RegisterUser(user);
        //return user.getFirstName() + " " + user.getLastName();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            headers = {"content-type=application/json"})
    public String loginUser(@RequestBody LoginDTO loginDTO) {
        return authService.LoginUser(loginDTO);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshToken(String token) {
        return "NYI";
    }
}
