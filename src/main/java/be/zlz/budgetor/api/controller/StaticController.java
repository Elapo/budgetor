package be.zlz.budgetor.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class StaticController {

    @GetMapping("/")
    public String index(Map<String, Object> model){
        model.put("title", "Hi");
        model.put("content", "Some content.");
        return "index";
    }

    /*@GetMapping("/error")
    public String error(){
        return "broken AF";
    }*/
}
