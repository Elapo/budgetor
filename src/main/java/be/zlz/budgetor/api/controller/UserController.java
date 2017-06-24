package be.zlz.budgetor.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String testThisApp() {
        return "Hello, World!";
    }

    @RequestMapping("/{id}")
    public String getUserById(@PathVariable String id) {
        return id;
    }
}
