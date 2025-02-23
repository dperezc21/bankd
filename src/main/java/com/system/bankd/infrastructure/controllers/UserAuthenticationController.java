package com.system.bankd.infrastructure.controllers;


import com.system.bankd.application.AuthUserUseCases;
import com.system.bankd.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/users")
public class UserAuthenticationController {

    @Autowired private AuthUserUseCases repository;

    @PostMapping(path = "/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User userSaved = null;
        try {
            userSaved = this.repository.registerUser(user);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok(userSaved);
    }
}
