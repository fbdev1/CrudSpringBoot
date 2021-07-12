package ru.jm.CrudSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jm.CrudSpringBoot.dao.UserDao;
import ru.jm.CrudSpringBoot.model.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
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
