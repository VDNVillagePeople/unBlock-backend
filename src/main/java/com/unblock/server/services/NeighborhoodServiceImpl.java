package com.unblock.server.services;

import com.unblock.server.data.storage.Block;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.Point;
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
public class NeighborhoodServiceImpl implements NeighborhoodService {

  @Autowired private NeighborhoodRepository neighborhoodRepository;

  @Override
  public Neighborhood create(Neighborhood neighborhood) {
    return neighborhoodRepository.save(neighborhood);
  }

  @Override
  public Optional<Neighborhood> getById(int id) {
    Optional<Neighborhood> neighborhood = neighborhoodRepository.findById(id).stream().findFirst();

    return neighborhood;
  }

  @Override
  public Neighborhood save(Neighborhood request) {
    return neighborhoodRepository.save(request);
  }

  @Override
  public List<Neighborhood> getAll() {
    return neighborhoodRepository.findAll();
  }
}
