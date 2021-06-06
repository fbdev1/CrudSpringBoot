package ru.jm.CrudSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.jm.CrudSpringBoot.dao.UserDao;
import ru.jm.CrudSpringBoot.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    private final UserDao userDao;

    @Autowired
    public HelloController(UserDao userService) {
        this.userDao = userService;
    }

    @GetMapping(value = "/admin")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userDao.listUsers());
        return "admin/admin";
    }

    @GetMapping("admin/findOne")
    @ResponseBody
    public User findOne(ModelMap model, Long id) {
        User user = userDao.findById(id);
        model.addAttribute("user",user);
        return user;

    }

    @PostMapping("/update")
    public String update(User user) {
        userDao.update(user, user.getId());
        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String create(User user) {
        userDao.add(user);
        return "redirect:/admin";
    }






    @GetMapping("admin/user-create")
    public String createUserForm(User user) {
        return "admin/user-create";
    }

    @PostMapping("admin/user-create")
        public String createUser(User user) {
        userDao.add(user);
        return "redirect:/admin";
    }


    @GetMapping("admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userDao.remove(id);
        return "redirect:/admin";
    }


    @GetMapping("admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, ModelMap model) {
        User user = userDao.findById(id);
        model.addAttribute("user", user);
        return "admin/user-update";
    }

    @PostMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user) {
        userDao.update(user, id);
        return "redirect:/admin";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}