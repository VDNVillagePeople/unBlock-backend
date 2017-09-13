package com.unblock.server.repositories;

import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Integer> {
    List<Neighborhood> findById(int id);
}
