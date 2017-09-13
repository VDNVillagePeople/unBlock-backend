package com.unblock.server.services;

import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public interface NeighborhoodService {
    Neighborhood create(Neighborhood neighborhood);

    Optional<Neighborhood> getById(int id);
}

