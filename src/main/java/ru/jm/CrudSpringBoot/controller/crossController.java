package ru.jm.CrudSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jm.CrudSpringBoot.dao.UserDao;
import ru.jm.CrudSpringBoot.model.User;

import java.security.Principal;

@Controller
public class crossController {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/admin")
    public String printUsers() {
        return "admin/admin";
    }

    @GetMapping(value = "/user")
    public String printWelcome(ModelMap model, Principal principal) {
        User user = userDao.getUserByName(principal.getName());
        model.addAttribute("user", user);
        return "admin/user";
    }

    @GetMapping(value = "/")
    public String loginPage() {
        return "redirect:/login";
    }

}
