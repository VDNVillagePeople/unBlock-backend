package com.unblock.server.data.storage.converters;

import com.unblock.proto.CityOuterClass;
import com.unblock.server.data.storage.City;

import java.util.stream.Collectors;

public class CityConverter {

  public static CityOuterClass.City toProto(City city) {
    return CityOuterClass.City.newBuilder()
        .setId(city.getId())
        .setStatus(city.getStatus())
        .setName(city.getName())
        .setImageFilename(city.getImageFilename())
        .addAllNeighborhoods(
            city
                .getNeighborhoods()
                .stream()
                .map(NeighborhoodConverter::toProto)
                .collect(Collectors.toList()))
        .build();
  }
}
