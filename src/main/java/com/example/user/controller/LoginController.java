package com.example.user.controller;

import com.example.user.entity.User;
import com.example.user.service.JwtService;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return user1;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody User user){
        String result = "";
        HttpStatus httpStatus = null;
//        log.info(user.toString());
        try {
            if(userService.checkLogin(user)){
                result = jwtService.generateTokenLogin(user.getPhone());
                httpStatus = HttpStatus.OK;
            }else {
                result = "Wrong username and password";
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception e) {
            result = "Server Error";
            httpStatus = HttpStatus.OK;
            e.printStackTrace();
        }
        return new ResponseEntity<>(result,httpStatus);
    }

}
