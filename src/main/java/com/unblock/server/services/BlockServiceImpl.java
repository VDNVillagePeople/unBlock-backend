package com.unblock.server.services;

import com.unblock.server.data.storage.Block;
import com.unblock.server.repositories.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public Block update(Block block) {
    return blockRepository.save(block);
  }

  @Override
  public Optional<Block> getById(int id) {
    return blockRepository.findById(id).stream().findFirst();
  }
}
