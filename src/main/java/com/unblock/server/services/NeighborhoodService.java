package com.unblock.server.services;

import com.unblock.server.data.storage.Neighborhood;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface NeighborhoodService {
  Neighborhood create(Neighborhood request);

  Neighborhood save(Neighborhood request);

  Optional<Neighborhood> getById(String id);

  List<Neighborhood> listAll();

  List<Neighborhood> listByCity(String cityId);
}
