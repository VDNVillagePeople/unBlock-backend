package com.unblock.server.services;

import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.User;
import com.unblock.server.repositories.NeighborhoodRepository;
import com.unblock.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class NeighborhoodServiceImpl implements NeighborhoodService {

    @Autowired
    private NeighborhoodRepository neighborhoodRepository;

    @Override
    public Neighborhood create(Neighborhood neighborhood) {
        return neighborhoodRepository.save(neighborhood);
    }

    @Override
    public Optional<Neighborhood> getById(int id) {
        return neighborhoodRepository.findById(id).stream().findFirst();
    }
}

