package com.unblock.server.data.storage.converters;

import com.unblock.proto.BoundsOuterClass.Bounds;
import com.unblock.proto.CityOuterClass;
import com.unblock.proto.PointOuterClass;
import com.unblock.server.data.storage.City;
import com.unblock.server.data.storage.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public class CityConverter {

  @Autowired
  private NeighborhoodConverter neighborhoodConverter;

  public CityOuterClass.City toProto(City city) {
    return CityOuterClass.City.newBuilder()
        .setId(city.getId())
        .setStatus(city.getStatus())
        .setName(city.getName())
        .setImageFilename(city.getImageFilename() == null ? "" : city.getImageFilename())
        .addAllNeighborhoods(
            city.getNeighborhoods()
                .stream()
                .map(neighborhood -> neighborhoodConverter.toProto(neighborhood))
                .collect(Collectors.toList()))
        .setCenter(PointOuterClass.Point.newBuilder().setX(city.getX()).setY(city.getY()))
        .setBounds(
            Bounds.newBuilder()
                .addAllPoints(
                    city.getBounds()
                        .stream()
                        .sorted(Comparator.comparingInt(Point::getOrderIndex))
                        .map(PointConverter::toProto)
                        .collect(Collectors.toList())))
        .build();
  }
}
