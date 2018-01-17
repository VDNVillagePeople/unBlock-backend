package com.unblock.server.data.storage.converters;

import com.unblock.proto.AttractionOuterClass;
import com.unblock.proto.AttractionOuterClass.AttractionLocation;
import com.unblock.server.data.storage.Attraction;

public class AttractionConverter {

  public static AttractionOuterClass.Attraction toProto(Attraction attraction) {
    return AttractionOuterClass.Attraction.newBuilder()
        .setId(attraction.getId())
        .setStatus(attraction.getStatus())
        .setBlockId(attraction.getBlock().getId())
        .setName(attraction.getName())
        .setLocation(
            AttractionLocation.newBuilder().setX(attraction.getX()).setY(attraction.getY()))
        .build();
  }
}
