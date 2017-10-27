package com.unblock.server.services;

import com.unblock.server.data.storage.User;
import com.unblock.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Override
  public void create(User user) {
    userRepository.save(user);
  }

  @Override
  public Optional<User> getByUsername(String username) {
    return userRepository.findByUsername(username).stream().findFirst();
  }

  @Override
  public Optional<User> getByEmail(String email) {
    return userRepository.findByEmail(email).stream().findFirst();
  }
}
