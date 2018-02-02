package com.unblock.server.services;

import com.unblock.server.data.storage.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface UserService {
  User create(User user);

  User save(User request);

  Optional<User> getById(String id);

  Optional<User> getByUsername(String username);

  Optional<User> getByEmail(String email);

  List<User> listAll();
}
