package com.unblock.server;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "Login successful!";
    }

    @RequestMapping("/create")
    public String create() {
        return "Create not implemented yet";
    }

}