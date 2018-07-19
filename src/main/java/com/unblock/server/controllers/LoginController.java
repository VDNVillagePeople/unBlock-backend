package com.unblock.server.controllers;

import com.unblock.proto.Login.LoginRequest;
import com.unblock.proto.UserOuterClass;
import com.unblock.server.data.storage.User;
import com.unblock.server.data.storage.converters.UserConverter;
import com.unblock.server.exception.LoginFailedException;
import com.unblock.server.security.TokenAuthenticationService;
import com.unblock.server.security.password.PasswordManager;
import com.unblock.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @RequestMapping(value = "/v1/login", method = RequestMethod.POST)
  public ResponseEntity<UserOuterClass.User> login(@RequestBody LoginRequest loginRequest)
      throws LoginFailedException {
    System.out.println("Request");
    System.out.println("usernameoremail: " + loginRequest.getUsernameOrEmail());
    System.out.println(loginRequest);
    User user = getUser(loginRequest);
    checkPassword(loginRequest.getPassword(), user.getPassword());

    HttpHeaders responseHeaders = new HttpHeaders();
    TokenAuthenticationService.addAuthentication(responseHeaders, user.getUsername());

    return new ResponseEntity<UserOuterClass.User>(
        UserConverter.toProto(user), responseHeaders, HttpStatus.OK);
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
