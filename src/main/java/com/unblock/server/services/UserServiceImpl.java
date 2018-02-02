package com.unblock.server.services;

import com.unblock.server.data.storage.User;
import com.unblock.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Override
  public User create(User user) {
    return userRepository.save(user);
  }

  @Override
  public User save(User request) {
    return userRepository.save(request);
  }
  
  @Override
  public Optional<User> getById(String id) {
    return userRepository.findById(id).stream().findFirst();
  }

  @Override
  public Optional<User> getByUsername(String username) {
    return userRepository.findByUsername(username).stream().findFirst();
  }

  @Override
  public Optional<User> getByEmail(String email) {
    return userRepository.findByEmail(email).stream().findFirst();
  }

  @Override
  public List<User> listAll() {
    return userRepository.findAll();
  }
}
