package com.system.bankd.infrastructure.controllers;


import com.system.bankd.application.AuthUserUseCases;
import com.system.bankd.domain.models.User;
import com.system.bankd.domain.responses.AuthUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/users")
public class UserAuthenticationController {

    @Autowired private AuthUserUseCases authUserUseCases;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthUserResponse> registerUser(@RequestBody User user) {
        AuthUserResponse userSaved = null;
        try {
            userSaved = this.authUserUseCases.registerUser(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(userSaved);
    }
}
