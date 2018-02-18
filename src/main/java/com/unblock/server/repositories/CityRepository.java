package com.unblock.server.repositories;

import com.unblock.server.data.storage.City;
import com.unblock.server.data.storage.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, String> {
  List<City> findById(String id);

  List<City> findAll();
}
