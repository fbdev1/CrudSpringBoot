package ru.jm.CrudSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.jm.CrudSpringBoot.model.User;
import ru.jm.CrudSpringBoot.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
public class HelloController {
    private final UserService userService;

    @Autowired
    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public User getCurrentUser(Principal principal) {
        User user = userService.getUserByName(principal.getName());
        user.setStringRoles(user.getRole());
        return user;
    }

    @GetMapping(value = "/adminAll")
    public List<User> getAllUsers() {
        List<User> newList = userService.listUsers();
        for (User u : newList) {
            u.setStringRoles(u.getRole());
        }
        return newList;
    }

    @GetMapping("/findOne/{id}")
    public User findOneById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return user;
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
        userService.update(user, user.getId());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.remove(id);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            userService.add(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
