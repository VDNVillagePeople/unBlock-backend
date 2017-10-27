package com.unblock.server.services;

import com.unblock.server.data.storage.Block;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public interface BlockService {
  Block create(Block request);

  Block update(Block request);

  Optional<Block> getById(int id);
}
