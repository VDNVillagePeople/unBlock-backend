package com.unblock.server.services;

import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlaceDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GooglePlacesServiceImpl implements GooglePlacesService {

  private static final String API_KEY = "AIzaSyBctSk6Ir_oEO77F6FLj1Z04g3IP0bCLyY";
  private static final GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();

  @Override
  public PlaceDetails getPlaceDetails(String googlePlaceId) throws Exception{
    PlaceDetailsRequest request = PlacesApi.placeDetails(context, googlePlaceId);
    return request.await();
  }
}
