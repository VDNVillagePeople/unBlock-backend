package com.unblock.server.controllers;

import com.unblock.proto.NewUser;
import com.unblock.server.data.storage.User;
import com.unblock.server.data.wire.UserWire;
import com.unblock.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void newUser(@RequestBody NewUser newUser) {
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());

        userService.create(user);
    }
}
