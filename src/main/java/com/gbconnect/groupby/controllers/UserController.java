package com.gbconnect.groupby.controllers;

import com.gbconnect.groupby.domain.Login;
import com.gbconnect.groupby.domain.Token;
import com.gbconnect.groupby.domain.User;
import com.gbconnect.groupby.services.JWTService;
import com.gbconnect.groupby.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;
    private final JWTService jwtService;

    public UserController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @RequestMapping("/users/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping("/users/getToken")
    public Token getJWTToken(@Valid @RequestBody Login login) {
        return jwtService.generateToken(login);
    }

    @RequestMapping("/users/profile")
    public User getUserProfile() {
        return userService.getCurrentUser();
    }
}
