package ru.jm.CrudSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jm.CrudSpringBoot.model.User;
import ru.jm.CrudSpringBoot.service.UserService;
import ru.jm.CrudSpringBoot.service.UserServiceImpl;

import java.security.Principal;

@Controller
public class CrossController {
    private UserService userService;

    public CrossController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/admin")
    public String printAllUsers() {
        return "admin/admin";
    }

    @GetMapping(value = "/user")
    public String printCurrentUser(ModelMap model, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user);
        return "admin/user";
    }

    @GetMapping(value = "/")
    public String loginPage() {
        return "redirect:/login";
    }

}
