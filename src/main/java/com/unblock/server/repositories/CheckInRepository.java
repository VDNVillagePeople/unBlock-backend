package com.unblock.server.repositories;

import com.unblock.server.data.storage.CheckIn;
import com.unblock.server.data.storage.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, String> {
  List<CheckIn> findById(String id);

  List<CheckIn> findByUserId(String userId);
}
