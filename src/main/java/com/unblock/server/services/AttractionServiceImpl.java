package com.unblock.server.services;

import com.unblock.server.data.storage.Attraction;
import com.unblock.server.data.storage.Block;
import com.unblock.server.repositories.AttractionRepository;
import com.unblock.server.repositories.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttractionServiceImpl implements AttractionService {

  @Autowired private AttractionRepository attractionRepository;

  @Override
  public Attraction create(Attraction block) {
    return attractionRepository.save(block);
  }

  @Override
  public Attraction save(Attraction block) {
    return attractionRepository.save(block);
  }

  @Override
  public Optional<Attraction> getById(String id) {
    return attractionRepository.findById(id).stream().findFirst();
  }

  @Override
  public List<Attraction> listAll() { return attractionRepository.findAll(); }

  @Override
  public List<Attraction> listByBlock(String blockId) {
    return attractionRepository.findByBlockId(blockId);
  }
}
