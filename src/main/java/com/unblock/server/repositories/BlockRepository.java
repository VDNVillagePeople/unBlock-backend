package com.unblock.server.repositories;

import com.unblock.server.data.storage.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Integer> {
  List<Block> findById(int id);
}