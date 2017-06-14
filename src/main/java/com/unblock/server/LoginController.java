package com.unblock.server;

import com.unblock.server.data.User;
import com.unblock.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login")
    public String login() {
        return "Login successful!";
    }

    @RequestMapping("/create")
    public String create() {
        User newUser = new User();
        newUser.setEmail("danny@dannyfranklin.com");
        newUser.setPassword("password");
        newUser.setUsername("dantheman");
        userRepository.save(newUser);
        newUser = userRepository.getOne(1);

        return newUser.toString();
    }
}