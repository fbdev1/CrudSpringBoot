package ru.jm.CrudSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jm.CrudSpringBoot.dao.UserDao;
import ru.jm.CrudSpringBoot.model.User;

import java.security.Principal;
import java.util.List;

@RestController
public class HelloController {
    private final UserDao userDao;

    @Autowired
    public HelloController(UserDao userService) {
        this.userDao = userService;
    }

    @GetMapping("/users")
    public User printWelcome(Principal principal) {
        User user = userDao.getUserByName(principal.getName());
        user.setStringRoles(user.getRole());
        return user;
    }

    @GetMapping(value = "/adminAll")
    public List<User> printUsers() {
        List<User> newList = userDao.listUsers();
        for (User u : newList) {
            u.setStringRoles(u.getRole());
        }
        return newList;
    }

    @GetMapping("/findOne/{id}")
    public User findOne(@PathVariable("id") Long id) {
        User user = userDao.findById(id);
        return user;
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        try {
        userDao.update(user, user.getId());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        userDao.remove(id);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            userDao.add(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
