package com.unblock.server.services;

import com.unblock.server.data.storage.CheckIn;
import com.unblock.server.data.storage.Neighborhood;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface CheckInService {
  CheckIn create(CheckIn request);

  CheckIn save(CheckIn request);

  Optional<CheckIn> getById(String id);

  List<CheckIn> listByUser(String userId);
}

