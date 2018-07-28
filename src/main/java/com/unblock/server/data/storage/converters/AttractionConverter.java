package com.unblock.server.data.storage.converters;

import com.unblock.proto.AttractionOuterClass;
import com.unblock.proto.PointOuterClass.Point;
import com.unblock.server.data.storage.Attraction;

public class AttractionConverter {

  public static AttractionOuterClass.Attraction toProto(Attraction attraction) {
    return AttractionOuterClass.Attraction.newBuilder()
        .setId(attraction.getId())
        .setStatus(attraction.getStatus())
        .setDescription(attraction.getDescription())
        .setBlockId(attraction.getBlock().getId())
        .setName(attraction.getName())
        .setLocation(Point.newBuilder().setX(attraction.getX()).setY(attraction.getY()))
        .setGooglePlaceId(attraction.getGooglePlaceId() == null ? "" : attraction.getGooglePlaceId())
        .build();
  }
}
