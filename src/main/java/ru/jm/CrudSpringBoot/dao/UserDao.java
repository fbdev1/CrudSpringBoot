package ru.jm.CrudSpringBoot.dao;

import ru.jm.CrudSpringBoot.model.User;

import java.util.List;

public interface UserDao{
    void add(User user);
    void remove(Long id);
    void update(User user,Long id);
    List<User> listUsers();
    User findById(Long id);
    User getUserByName(String name);



}
