package com.unblock.server.data.storage.converters;

import com.google.maps.model.PlaceDetails;
import com.unblock.proto.AttractionOuterClass;
import com.unblock.proto.PointOuterClass.Point;
import com.unblock.server.data.storage.Attraction;
import com.unblock.server.services.GooglePlacesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttractionConverter {

  Logger logger = LoggerFactory.getLogger(AttractionConverter.class);

  @Autowired private GooglePlacesService googlePlacesService;

  public AttractionOuterClass.Attraction toProto(Attraction attraction) {
    AttractionOuterClass.Attraction.Builder result =
        AttractionOuterClass.Attraction.newBuilder()
            .setId(attraction.getId())
            .setStatus(attraction.getStatus())
            .setBlockId(attraction.getBlock().getId());

    if (!attraction.getGooglePlaceId().trim().isEmpty()) {
      try {
        PlaceDetails details = googlePlacesService.getPlaceDetails(attraction.getGooglePlaceId());
        result
            .setLocation(
                Point.newBuilder()
                    .setX((float) details.geometry.location.lat)
                    .setY((float) details.geometry.location.lng))
            .setName(details.name);
      } catch (Exception e) {
        logger.error("Error getting places service details", e);
      }
    } else {
      result.setLocation(Point.newBuilder().setX(attraction.getX()).setY(attraction.getY()))
          .setName(attraction.getName());
    }

    if (attraction.getDescription() != null) {
      result.setDescription(attraction.getDescription());
    }

    return result.build();
  }
}
