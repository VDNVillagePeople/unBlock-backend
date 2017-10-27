package com.unblock.server.data.storage.converters;

import com.unblock.proto.AttractionOuterClass;
import com.unblock.proto.BlockOuterClass.Bounds;
import com.unblock.server.data.storage.Attraction;
import com.unblock.server.data.storage.Block;
import com.unblock.server.data.storage.Point;

import java.util.Comparator;
import java.util.stream.Collectors;

public class AttractionConverter {

  public static AttractionOuterClass.Attraction toProto(Attraction attraction) {
    return AttractionOuterClass.Attraction.newBuilder()
        .setId(Integer.toString(attraction.getId()))
        .setName(attraction.getTitle())
        .setX(attraction.getX())
        .setY(attraction.getY())
        .build();
  }
}