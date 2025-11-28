package com.sat.tmf.movietkt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sat.tmf.movietkt.dao.UserDao;
import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User register(User user) {
        if (userDao.findByUsername(user.getUsername()) != null)
            throw new RuntimeException("Username already exists!");
        if (userDao.findByEmail(user.getEmail()) != null)
            throw new RuntimeException("Email already exists!");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return user;
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User updateUserProfile(String username, User updatedUser) {
        User existing = userDao.findByUsername(username);
        if (existing == null) {
            throw new RuntimeException("User not found!");
        }
        existing.setFullName(updatedUser.getFullName());
        existing.setEmail(updatedUser.getEmail());
        existing.setPhone(updatedUser.getPhone());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().trim().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        userDao.saveOrUpdate(existing); 
        return existing;
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }
}
