package com.unblock.server.controllers;

import com.unblock.proto.LoginRequest;
import com.unblock.server.data.storage.User;
import com.unblock.server.exception.LoginFailedException;
import com.unblock.server.security.TokenAuthenticationService;
import com.unblock.server.security.password.PasswordManager;
import com.unblock.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class LoginController {
  @Autowired private UserService userService;

  @Autowired private PasswordManager passwordManager;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public void login(@RequestBody LoginRequest loginRequest, HttpServletResponse response)
      throws LoginFailedException {
    System.out.println("Request");
    System.out.println("usernameoremail: " + loginRequest.getUsernameOrEmail());
    System.out.println(loginRequest);
    User user = getUser(loginRequest);
    checkPassword(loginRequest.getPassword(), user.getPassword());

    TokenAuthenticationService.addAuthentication(response, user.getUsername());
    response.setStatus(204);
  }

  private User getUser(LoginRequest loginRequest) throws LoginFailedException {
    Optional<User> user = userService.getByUsername(loginRequest.getUsernameOrEmail());
    if (!user.isPresent()) {
      user = userService.getByEmail(loginRequest.getUsernameOrEmail());
    }
    if (!user.isPresent()) {
      throwUnauthorized();
    }

    return user.get();
  }

  private void checkPassword(String submittedPassword, String retrievedPassword)
      throws LoginFailedException {
    if (!passwordManager.checkPassword(submittedPassword, retrievedPassword)) {
      throwUnauthorized();
    }
  }

  private void throwUnauthorized() throws LoginFailedException {
    throw new LoginFailedException();
  }
}
