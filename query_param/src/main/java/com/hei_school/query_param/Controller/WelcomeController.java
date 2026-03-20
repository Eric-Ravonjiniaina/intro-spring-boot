package com.hei_school.query_param.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/welcome")
    public String welcomeUser(@RequestParam(value = "name", defaultValue = "Guest") String name) {
        return "Welcome " + name;
    }
}
