package com.unblock.server.services;

import com.unblock.server.data.storage.Block;
import com.unblock.server.data.storage.City;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.Point;
import com.unblock.server.repositories.CityRepository;
import com.unblock.server.repositories.NeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl implements CityService {

  @Autowired private CityRepository cityRepository;

  @Override
  public City create(City city) {
    return cityRepository.save(city);
  }

  @Override
  public Optional<City> getById(String id) {
    return cityRepository.findById(id).stream().findFirst();
  }

  @Override
  public City save(City request) {
    return cityRepository.save(request);
  }

  @Override
  public List<City> listAll() {
    return cityRepository.findAll();
  }
}
