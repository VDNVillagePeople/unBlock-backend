package com.unblock.server.data.storage.converters;

import com.unblock.server.data.storage.Neighborhood;

import java.util.stream.Collectors;

public class NeighborhoodConverter {

  public static com.unblock.proto.Neighborhood toProto(Neighborhood neighborhood) {
    return com.unblock.proto.Neighborhood.newBuilder()
        .addAllBlocks(
            neighborhood
                .getBlocks()
                .stream()
                .map(BlockConverter::toProto)
                .collect(Collectors.toList()))
        .build();
  }
}
