package com.unblock.server.services;

import com.google.maps.model.PlaceDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface GooglePlacesService {
  PlaceDetails getPlaceDetails(String googlePlaceId) throws Exception;
}
