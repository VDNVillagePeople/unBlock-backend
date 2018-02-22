package com.unblock.server.services;

import com.unblock.server.data.storage.Attraction;
import com.unblock.server.data.storage.Block;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface AttractionService {
  Attraction create(Attraction request);

  Attraction save(Attraction request);

  Optional<Attraction> getById(String id);

  List<Attraction> listAll();

  List<Attraction> listByBlock(String blockId);
}
