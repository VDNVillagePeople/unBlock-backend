package com.unblock.server.repositories;

import com.unblock.server.data.storage.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
  List<User> findById(String id);

  List<User> findByUsername(String username);

  List<User> findByEmail(String email);
}
