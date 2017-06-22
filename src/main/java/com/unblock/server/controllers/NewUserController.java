package com.unblock.server.controllers;

import com.unblock.proto.NewUser;
import com.unblock.server.data.storage.User;
import com.unblock.server.exception.EmailAlreadyExistsException;
import com.unblock.server.exception.UsernameAlreadyExistsException;
import com.unblock.server.security.TokenAuthenticationService;
import com.unblock.server.security.password.PasswordManager;
import com.unblock.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class NewUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordManager passwordManager;

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void newUser(@RequestBody NewUser newUser, HttpServletResponse response) throws Exception {
        verifyUsernameUniqueness(newUser.getUsername());
        verifyEmailUniqueness(newUser.getEmail());

        createUser(newUser);
        TokenAuthenticationService.addAuthentication(response, newUser.getUsername());
    }

    private void verifyUsernameUniqueness(String username) throws UsernameAlreadyExistsException {
        Optional<User> user = userService.getByUsername(username);
        if (user.isPresent()) {
            throw new UsernameAlreadyExistsException();
        }
    }

    private void verifyEmailUniqueness(String email) throws EmailAlreadyExistsException {
        Optional<User> user = userService.getByEmail(email);
        if (user.isPresent()) {
            throw new EmailAlreadyExistsException();
        }
    }

    private void createUser(NewUser newUser) {
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(passwordManager.getEncryptedPassword(newUser.getPassword()));
        userService.create(user);
    }
}
