package com.sda.springbootresthibernate.repository;

import com.sda.springbootresthibernate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepoInterface extends JpaRepository<User, Long> {

    @Query(value = "select u from User u where u.age > 20")
    List<User> getAllUsersAbove20();

//    @Query(value = "select * from user where age > 20", nativeQuery = true)
//    List<User> getAllUsersAbove20();

}
