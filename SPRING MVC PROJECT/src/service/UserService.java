package com.sat.tmf.movietkt.service;

import java.util.List;

import com.sat.tmf.movietkt.entities.User;

public interface UserService {
    User register(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User updateUserProfile(String username, User updatedUser);
    List<User> findAllUsers();
    User authenticate(String username, String password); 
}
