package com.unblock.server.data.storage.converters;

import com.unblock.proto.NeighborhoodOuterClass;
import com.unblock.server.data.storage.Neighborhood;

import java.util.stream.Collectors;

public class NeighborhoodConverter {

  public static NeighborhoodOuterClass.Neighborhood toProto(Neighborhood neighborhood) {
    return NeighborhoodOuterClass.Neighborhood.newBuilder()
        .setId(neighborhood.getId())
        .setStatus(neighborhood.getStatus())
        .setCityId(neighborhood.getCity().getId())
        .setName(neighborhood.getName())
        .addAllBlocks(
            neighborhood
                .getBlocks()
                .stream()
                .map(BlockConverter::toProto)
                .collect(Collectors.toList()))
        .build();
  }
}
