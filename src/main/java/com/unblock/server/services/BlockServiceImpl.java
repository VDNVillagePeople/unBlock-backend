package com.unblock.server.services;

import com.unblock.server.data.storage.Block;
import com.unblock.server.repositories.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlockServiceImpl implements BlockService {

  @Autowired private BlockRepository blockRepository;

  @Override
  public Block create(Block block) {
    return blockRepository.save(block);
  }

  @Override
  public Block save(Block block) {
    return blockRepository.save(block);
  }

  @Override
  public Optional<Block> getById(String id) {
    return blockRepository.findById(id).stream().findFirst();
  }

  @Override
  public List<Block> listAll() { return blockRepository.findAll(); }

  @Override
  public List<Block> listByNeighborhood(String neighborhoodId) {
    return blockRepository.findByNeighborhoodId(neighborhoodId);
  }
}
