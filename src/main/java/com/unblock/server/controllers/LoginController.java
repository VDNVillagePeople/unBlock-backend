package com.unblock.server.controllers;

import com.unblock.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "Login successful!";
    }
}