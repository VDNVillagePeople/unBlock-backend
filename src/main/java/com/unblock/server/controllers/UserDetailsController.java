package com.unblock.server.controllers;

import com.unblock.proto.Login;
import com.unblock.server.exception.LoginFailedException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserDetailsController {
    @RequestMapping("/get")
    public String get() throws LoginFailedException {
        return "Success!";
    }
}
