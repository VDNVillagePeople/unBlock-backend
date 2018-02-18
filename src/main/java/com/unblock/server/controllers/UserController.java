package com.unblock.server.controllers;

import com.unblock.proto.NewUser.NewUserRequest;
import com.unblock.proto.UserOuterClass;
import com.unblock.proto.UserOuterClass.CreateUserRequest;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.User;
import com.unblock.server.data.storage.converters.UserConverter;
import com.unblock.server.exception.EmailAlreadyExistsException;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.exception.UsernameAlreadyExistsException;
import com.unblock.server.security.TokenAuthenticationService;
import com.unblock.server.security.password.PasswordManager;
import com.unblock.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

  @Autowired private UserService userService;

  @Autowired private PasswordManager passwordManager;

  @RequestMapping(value = "/v1/user", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.OK)
  public void createUser(
      @RequestBody CreateUserRequest createUserRequest, HttpServletResponse response)
      throws Exception {
    verifyUsernameUniqueness(createUserRequest.getInfo().getUsername());
    verifyEmailUniqueness(createUserRequest.getInfo().getEmail());

    User user = createUser(createUserRequest);
    TokenAuthenticationService.addAuthentication(response, user.getId());
  }

  @RequestMapping(value = "/v1/user/{userId}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public UserOuterClass.User getUser(@PathVariable String userId) throws Exception {
    User user =
        userService.getById(userId).orElseThrow(ResourceNotFoundException::new);
    return UserConverter.toProto(user);
  }

  @RequestMapping(value = "/v1/users", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public UserOuterClass.ListUsersResponse listUsers() throws Exception {
    return UserOuterClass.ListUsersResponse.newBuilder()
        .addAllUsers(
            userService
                .listAll()
                .stream()
                .map(UserConverter::toProto)
                .collect(Collectors.toList()))
        .build();
  }

  @RequestMapping(value = "/v1/user:info", method = RequestMethod.PATCH)
  public UserOuterClass.User updateUserInfo(
      @RequestBody UserOuterClass.UpdateUserInfoRequest request) throws Exception {
    User user = userService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    user.setUsername(request.getInfo().getUsername());
    user.setEmail(request.getInfo().getEmail());
    user.setLevel(request.getInfo().getLevel().name());
    return UserConverter.toProto(userService.save(user));
  }

  @RequestMapping(value = "/v1/user:password", method = RequestMethod.PATCH)
  public UserOuterClass.User updateUserPassword(
      @RequestBody UserOuterClass.UpdateUserPasswordRequest request) throws Exception {
    User user = userService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    user.setPassword(passwordManager.getEncryptedPassword(request.getInfo().getPassword()));
    return UserConverter.toProto(userService.save(user));
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

  private User createUser(CreateUserRequest createUserRequest) {
    User user = new User();
    user.setUsername(createUserRequest.getInfo().getUsername());
    user.setEmail(createUserRequest.getInfo().getEmail());
    user.setPassword(
        passwordManager.getEncryptedPassword(createUserRequest.getInfo().getPassword()));
    return userService.create(user);
  }
}
