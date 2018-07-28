package com.unblock.server.data.storage.converters;

import com.unblock.proto.BoundsOuterClass.Bounds;
import com.unblock.proto.CityOuterClass;
import com.unblock.proto.PointOuterClass.Point;
import com.unblock.server.data.storage.City;

import java.util.stream.Collectors;

public class CityConverter {

  public static CityOuterClass.City toProto(City city) {
    return CityOuterClass.City.newBuilder()
        .setId(city.getId())
        .setStatus(city.getStatus())
        .setName(city.getName())
        .setImageFilename(city.getImageFilename() == null ? "" : city.getImageFilename())
        .addAllNeighborhoods(
            city.getNeighborhoods()
                .stream()
                .map(NeighborhoodConverter::toProto)
                .collect(Collectors.toList()))
        .setCenter(Point.newBuilder().setX(city.getX()).setY(city.getY()))
        .setBounds(
            Bounds.newBuilder()
                .addAllPoints(
                    city.getBounds()
                        .stream()
                        .map(PointConverter::toProto)
                        .collect(Collectors.toList())))
        .build();
  }
}
