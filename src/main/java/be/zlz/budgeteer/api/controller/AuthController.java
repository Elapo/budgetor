package be.zlz.budgeteer.api.controller;

import be.zlz.budgeteer.api.Service.AuthService;
import be.zlz.budgeteer.api.domain.User;
import be.zlz.budgeteer.api.wrapper.LoginWrapper;
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
    public String registerUser(@RequestBody User user) {
        return authService.RegisterUser(user);
        //return user.getFirstName() + " " + user.getLastName();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            headers = {"content-type=application/json"})
    public String loginUser(@RequestBody LoginWrapper loginWrapper) {
        return loginWrapper.getEmail() + " " + loginWrapper.getPassword();
    }
}
