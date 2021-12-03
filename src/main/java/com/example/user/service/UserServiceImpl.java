package com.example.user.service;

import com.example.user.entity.User;
import com.example.user.responsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserReponsitory userReponsitory;

    @Override
    public User saveUser(User user) {
        return userReponsitory.saveAndFlush(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userReponsitory.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userReponsitory.findById(userId).get();
    }

    @Override
    public boolean deleteUser(Long userId) {
        try {
            userReponsitory.deleteById(userId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
