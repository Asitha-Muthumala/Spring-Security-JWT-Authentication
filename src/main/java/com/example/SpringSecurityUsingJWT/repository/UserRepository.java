package com.example.SpringSecurityUsingJWT.repository;

import com.example.SpringSecurityUsingJWT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String username);

}
