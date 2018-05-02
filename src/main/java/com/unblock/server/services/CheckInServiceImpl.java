package com.unblock.server.services;

import com.unblock.server.data.storage.CheckIn;
import com.unblock.server.repositories.CheckInRepository;
import com.unblock.server.repositories.NeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CheckInServiceImpl implements CheckInService {

  @Autowired
  private CheckInRepository checkInRepository;

  public CheckIn create(CheckIn request) {
    return checkInRepository.save(request);
  }

  public CheckIn save(CheckIn request) {
    return checkInRepository.save(request);
  }

  public Optional<CheckIn> getById(String id) {
    return checkInRepository.findById(id).stream().findFirst();
  }

  public List<CheckIn> listByUser(String userId) {
    return checkInRepository.findByUserId(userId);
  }
}
