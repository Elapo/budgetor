package be.zlz.budgeteer.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Frederik on 12/02/2017.
 */

@RestController
public class UserController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testThisApp(){
        return "Hello, World!";
    }
}
