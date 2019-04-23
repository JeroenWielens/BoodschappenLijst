package com.jwielens.grocery.controllers;

import com.jwielens.grocery.domain.User;
import com.jwielens.grocery.repositories.RoleRepository;
import com.jwielens.grocery.services.SecurityService;
import com.jwielens.grocery.services.UserService;
import com.jwielens.grocery.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class UserController {

    private UserService userService;
    private SecurityService securityService;
    private UserValidator userValidator;
    private RoleRepository roleRepository;

    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator, RoleRepository roleRepository) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out succesfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model){
        return "welcome";
    }

    @GetMapping("/users")
    public String allUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "users";
    }

    @RequestMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @RequestMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findByid(id));
        return "editUser";
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User userForm, BindingResult result, RedirectAttributes attributes) {

        Long currentId = userForm.getId();
        User currentUser = userService.findByid(currentId).get();
        String currentName = currentUser.getUsername();

        if ((userService.checkDupUser(userForm.getUsername())) && (!currentName.equals(userForm.getUsername()))) {
            System.out.println("username is een duplicaat");
            attributes.addFlashAttribute("message", "Deze username bestaat al, probeer het opnieuw");
            attributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/users/edit/" + currentId;
        }



        userService.save(userForm);

        return "redirect:/users";
    }
}
