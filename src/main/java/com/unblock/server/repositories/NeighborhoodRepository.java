package com.unblock.server.repositories;

import com.unblock.server.data.storage.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, String> {
  List<Neighborhood> findById(String id);

  List<Neighborhood> findByCityId(String cityId);

  List<Neighborhood> findAll();
}
