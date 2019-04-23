package com.jwielens.grocery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/accessDenied";
    }

    @RequestMapping("/")
    public void handleRequest() {
        throw new RuntimeException("test exception");
    }
}
