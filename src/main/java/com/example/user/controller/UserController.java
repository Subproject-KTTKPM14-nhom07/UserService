package com.example.user.controller;

import com.example.user.entity.Token;
import com.example.user.entity.User;
import com.example.user.service.JwtService;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User  getUserById(@PathVariable("id") Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        boolean rs = userService.deleteUser(id);
        if(rs){
            return "Success";
        }
        else{
            return "Fail";
        }

    }

    @PostMapping("/userFromToken")
    public User userfromtoken(@RequestBody User token) {
        log.info(token.toString());
        User user = userService.checkUserJWT(token.getName());
        return user;
    }

}
