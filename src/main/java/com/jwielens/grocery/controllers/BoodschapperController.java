package com.jwielens.grocery.controllers;

import com.jwielens.grocery.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sendemail")
public class BoodschapperController {
    private UserService userService;

    public BoodschapperController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getBoodschappers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "boodschappers";
    }
}
