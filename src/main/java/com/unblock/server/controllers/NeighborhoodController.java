package com.unblock.server.controllers;

import com.unblock.proto.BoundsOuterClass;
import com.unblock.proto.CityOuterClass;
import com.unblock.proto.NeighborhoodOuterClass;
import com.unblock.proto.NeighborhoodOuterClass;
import com.unblock.server.data.storage.City;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.Point;
import com.unblock.server.data.storage.converters.*;
import com.unblock.server.data.storage.converters.NeighborhoodConverter;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.services.CityService;
import com.unblock.server.services.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NeighborhoodController {

  @Autowired private NeighborhoodService neighborhoodService;
  @Autowired private CityService cityService;

  @Autowired private NeighborhoodConverter neighborhoodConverter;

  @RequestMapping(value = "/v1/neighborhood", method = RequestMethod.POST)
  public NeighborhoodOuterClass.Neighborhood createNeighborHood(
      @RequestBody NeighborhoodOuterClass.CreateNeighborhoodRequest request) throws Exception {
    City city =
        cityService.getById(request.getCityId()).orElseThrow(ResourceNotFoundException::new);
    Neighborhood neighborhood = new Neighborhood();
    neighborhood.setCity(city);
    neighborhood.setName(request.getInfo().getName());
    neighborhood.setStatus(NeighborhoodOuterClass.NeighborhoodStatus.NEIGHBORHOOD_LIVE);
    return neighborhoodConverter.toProto(neighborhoodService.create(neighborhood));
  }

  @RequestMapping(value = "/v1/city/{cityId}/neighborhoods", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public NeighborhoodOuterClass.ListNeighborhoodsResponse listNeighborhoodsByCity(
      @PathVariable String cityId) throws Exception {
    return NeighborhoodOuterClass.ListNeighborhoodsResponse.newBuilder()
            .addAllNeighborhoods(
                neighborhoodService
                    .listByCity(cityId)
                    .stream()
                    .map(neighborhoodConverter::toProto)
                    .collect(Collectors.toList()))
            .build();
  }

  @RequestMapping(value = "/v1/neighborhoods", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public NeighborhoodOuterClass.ListNeighborhoodsResponse listAllNeighborhood() throws Exception {
    return NeighborhoodOuterClass.ListNeighborhoodsResponse.newBuilder()
            .addAllNeighborhoods(
                neighborhoodService
                    .listAll()
                    .stream()
                    .map(neighborhoodConverter::toProto)
                    .collect(Collectors.toList()))
            .build();
  }

  @RequestMapping(value = "/v1/neighborhood/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public NeighborhoodOuterClass.Neighborhood getNeighborhood(@PathVariable String id)
      throws Exception {
    Neighborhood neighborhood =
        neighborhoodService.getById(id).orElseThrow(ResourceNotFoundException::new);
    return neighborhoodConverter.toProto(neighborhood);
  }

  @RequestMapping(value = "/v1/neighborhood:info", method = RequestMethod.PATCH)
  public NeighborhoodOuterClass.Neighborhood updateNeighborhoodInfo(
      @RequestBody NeighborhoodOuterClass.UpdateNeighborhoodInfoRequest request) throws Exception {
    Neighborhood neighborhood =
        neighborhoodService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    neighborhood.setName(request.getInfo().getName());
    return neighborhoodConverter.toProto(neighborhoodService.save(neighborhood));
  }

  @RequestMapping(value = "/v1/neighborhood:status", method = RequestMethod.PATCH)
  public NeighborhoodOuterClass.Neighborhood updateNeighborhoodStatus(
      @RequestBody NeighborhoodOuterClass.UpdateNeighborhoodStatusRequest request)
      throws Exception {
    Neighborhood neighborhood =
        neighborhoodService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    neighborhood.setStatus(request.getStatus());
    return neighborhoodConverter.toProto(neighborhoodService.save(neighborhood));
  }

  @RequestMapping(value = "/v1/neighborhood:assign", method = RequestMethod.PATCH)
  public NeighborhoodOuterClass.Neighborhood assignNeighborhoodToCity(
      @RequestBody NeighborhoodOuterClass.AssignNeighborhoodToCityRequest request)
      throws Exception {
    Neighborhood neighborhood =
        neighborhoodService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    City city =
        cityService.getById(request.getCityId()).orElseThrow(ResourceNotFoundException::new);
    neighborhood.setCity(city);
    return neighborhoodConverter.toProto(neighborhoodService.save(neighborhood));
  }

  @RequestMapping(value = "/v1/neighborhood:bounds", method = RequestMethod.PATCH)
  public NeighborhoodOuterClass.Neighborhood updateNeighborhoodBounds(
      @RequestBody NeighborhoodOuterClass.UpdateNeighborhoodBoundsRequest request) throws Exception {
    Neighborhood neighborhood = neighborhoodService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    setBounds(neighborhood, request.getUpdate().getBounds());
    return neighborhoodConverter.toProto(neighborhoodService.save(neighborhood));
  }

  private void setBounds(Neighborhood neighborhood, BoundsOuterClass.Bounds bounds) {
    List<Point> points =
        bounds.getPointsList().stream().map(PointConverter::toJava).collect(Collectors.toList());
    int index = 0;
    for (Point point : points) {
      point.setNeighborhood(neighborhood);
      point.setOrderIndex(index++);
    }
    neighborhood.setBounds(points);
  }
}
