package com.unblock.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Username or password incorrect.")
  @ExceptionHandler(LoginFailedException.class)
  public void loginFailed() {}

  @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Username already exists.")
  @ExceptionHandler(UsernameAlreadyExistsException.class)
  public void usernameExists() {}

  @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Email already exists.")
  @ExceptionHandler(EmailAlreadyExistsException.class)
  public void emailExists() {}

  @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource not found.")
  @ExceptionHandler(ResourceNotFoundException.class)
  public void ResourceNotFound() {}
}
