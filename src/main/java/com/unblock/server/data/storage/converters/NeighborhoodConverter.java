package com.unblock.server.data.storage.converters;

import com.unblock.proto.BoundsOuterClass;
import com.unblock.proto.NeighborhoodOuterClass;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.Point;

import java.util.Comparator;
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
        .setBounds(
            BoundsOuterClass.Bounds.newBuilder()
                .addAllPoints(
                    neighborhood.getBounds()
                        .stream()
                        .sorted(Comparator.comparingInt(Point::getOrderIndex))
                        .map(PointConverter::toProto)
                        .collect(Collectors.toList())))
        .build();
  }
}
