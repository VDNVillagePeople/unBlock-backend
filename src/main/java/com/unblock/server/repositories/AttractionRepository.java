package com.unblock.server.repositories;

import com.unblock.server.data.storage.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, String> {
  List<Attraction> findById(String id);

  List<Attraction> findByBlockId(String id);

  List<Attraction> findAll();
}
