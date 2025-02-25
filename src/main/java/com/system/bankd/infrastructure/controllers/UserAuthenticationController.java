package com.system.bankd.infrastructure.controllers;

import com.system.bankd.application.AuthUserUseCases;
import com.system.bankd.domain.models.User;
import com.system.bankd.domain.responses.AuthUserResponse;
import com.system.bankd.domain.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth/users")
public class UserAuthenticationController {

    @Autowired private AuthUserUseCases authUserUseCases;

    @PostMapping(path = "/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        AuthUserResponse userSaved;
        try {
            userSaved = this.authUserUseCases.registerUser(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new GenericResponse(e.getMessage(), null));
        }
        return ResponseEntity.ok(new GenericResponse("user saved", userSaved));
    }

    @GetMapping(path = "/login")
    public ResponseEntity userLogin(@RequestParam String userName, @RequestParam String password) {
        AuthUserResponse userAuthenticated;
        try {
            userAuthenticated = this.authUserUseCases.login(userName, password);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResponse(e.getMessage(), null));
        }
        return ResponseEntity.ok(new GenericResponse("user authenticated", userAuthenticated));
    }
}
