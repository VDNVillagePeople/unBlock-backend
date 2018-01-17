package com.unblock.server.services;

import com.unblock.server.data.storage.Block;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface BlockService {
  Block create(Block request);

  Block save(Block request);

  Optional<Block> getById(String id);

  List<Block> listByNeighborhood(String neighborhoodId);
}
