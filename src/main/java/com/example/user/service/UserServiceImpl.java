package com.example.user.service;

import com.example.user.entity.User;
import com.example.user.responsitory.UserReponsitory;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserReponsitory userReponsitory;

    @Autowired
    private JwtService jwtService;

    @Override
    public User saveUser(User user) {
        return userReponsitory.saveAndFlush(user);
    }

    @Override
//    @RateLimiter(name="user")
    public List<User> getAllUsers() {
        return userReponsitory.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userReponsitory.findById(userId).get();
    }

    //sử dụng ratelimier để giới hạn lượng request đi vào
    @Override
    @RateLimiter(name="user")
    public User getUserByIdRT(Long userId) {
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

    @Override
    public User getUserByPhone(String phone) {
        return userReponsitory.getUserByPhone(phone);
    }

    @Override
    public boolean checkLogin(User user) {
        User user1 = getUserByPhone(user.getPhone());
        if (StringUtils.equals(user.getPhone(), user1.getPhone())
                && StringUtils.equals(user.getPassword(), user1.getPassword())) {
            return true;
        }
        return  false;
    }

    @Override
    public User checkUserJWT(String token) {
        log.info("nhi nhi nhi");
        User user = null;
        if (jwtService.validateTokenLogin(token)) {
            String sdt = jwtService.getUsernameFromToken(token);
            log.info(sdt);
            user = getUserByPhone(sdt);

        }
        log.info(user.toString());

        return user;

    }
}
