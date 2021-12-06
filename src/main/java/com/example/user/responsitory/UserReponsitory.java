package com.example.user.responsitory;

import com.example.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReponsitory extends JpaRepository<User,Long > {

    @Query(value = "select * from user where phone = :phonenumber",nativeQuery = true)
    public User getUserByPhone(@Param("phonenumber") String phone);
}
