package ru.jm.CrudSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.jm.CrudSpringBoot.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserService userDao;

    public UserServiceImpl(UserService userDao) {
        this.userDao = userDao;
    }


    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void remove(Long id) {
        userDao.remove(id);
    }

    @Override
    public void update(User user, Long id) {
        userDao.update(user, id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByName(email);
    }
}
