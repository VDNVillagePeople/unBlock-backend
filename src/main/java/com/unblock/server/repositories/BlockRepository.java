package com.unblock.server.repositories;

import com.unblock.server.data.storage.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, String> {
  List<Block> findById(String id);

  List<Block> findByNeighborhoodId(String id);

  List<Block> findAll();
}
