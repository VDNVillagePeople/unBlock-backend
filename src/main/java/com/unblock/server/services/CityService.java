package com.unblock.server.services;

import com.unblock.server.data.storage.City;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface CityService {
  City create(City request);

  City save(City request);

  Optional<City> getById(String id);

  List<City> listAll();
}
