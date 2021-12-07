package com.example.user.service;


import com.example.user.entity.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
    public User getUserById(Long userId);
    public boolean deleteUser(Long userId);
    public User getUserByPhone(String phone);
    public boolean checkLogin(User user);
    public User checkUserJWT(String token);
}
