package be.zlz.budgeteer.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String testThisApp(){
        return "Hello, World!";
    }

    @RequestMapping("/{id}")
    public String getUserById(@PathVariable String id){
        return id;
    }
}
