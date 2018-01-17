package com.unblock.server.controllers;

import com.unblock.proto.NeighborhoodOuterClass;
import com.unblock.proto.NeighborhoodOuterClass;
import com.unblock.server.data.storage.City;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.converters.NeighborhoodConverter;
import com.unblock.server.data.storage.converters.NeighborhoodConverter;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.services.CityService;
import com.unblock.server.services.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class NeighborhoodController {

  @Autowired private NeighborhoodService neighborhoodService;
  @Autowired private CityService cityService;

  @RequestMapping(value = "/v1/neighborhood", method = RequestMethod.POST)
  public NeighborhoodOuterClass.Neighborhood createNeighborHood(
      @RequestBody NeighborhoodOuterClass.CreateNeighborhoodRequest request) {
    Neighborhood neighborhood = new Neighborhood();
    neighborhood.setName(request.getInfo().getName());
    neighborhood.setStatus(NeighborhoodOuterClass.NeighborhoodStatus.NEIGHBORHOOD_LIVE);
    return NeighborhoodConverter.toProto(neighborhoodService.create(neighborhood));
  }

  @RequestMapping(value = "/v1/city/{cityId}/neighborhoods/", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public NeighborhoodOuterClass.ListNeighborhoodsResponse listNeighborhoodsByCity(
      @PathVariable String cityId) throws Exception {
    NeighborhoodOuterClass.ListNeighborhoodsResponse temp =
        NeighborhoodOuterClass.ListNeighborhoodsResponse.newBuilder()
            .addAllNeighborhoods(
                neighborhoodService
                    .listByCity(cityId)
                    .stream()
                    .map(NeighborhoodConverter::toProto)
                    .collect(Collectors.toList()))
            .build();
    return temp;
  }

  @RequestMapping(value = "/v1/neighborhood/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public NeighborhoodOuterClass.Neighborhood getNeighborhood(@PathVariable String id)
      throws Exception {
    Neighborhood neighborhood =
        neighborhoodService.getById(id).orElseThrow(ResourceNotFoundException::new);
    return NeighborhoodConverter.toProto(neighborhood);
  }

  @RequestMapping(value = "/v1/neighborhood:info", method = RequestMethod.PATCH)
  public NeighborhoodOuterClass.Neighborhood updateNeighborhoodInfo(
      @RequestBody NeighborhoodOuterClass.UpdateNeighborhoodInfoRequest request) throws Exception {
    Neighborhood neighborhood =
        neighborhoodService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    neighborhood.setName(request.getInfo().getName());
    return NeighborhoodConverter.toProto(neighborhoodService.save(neighborhood));
  }

  @RequestMapping(value = "/v1/neighborhood:status", method = RequestMethod.PATCH)
  public NeighborhoodOuterClass.Neighborhood updateNeighborhoodStatus(
      @RequestBody NeighborhoodOuterClass.UpdateNeighborhoodStatusRequest request)
      throws Exception {
    Neighborhood neighborhood =
        neighborhoodService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    neighborhood.setStatus(request.getStatus());
    return NeighborhoodConverter.toProto(neighborhoodService.save(neighborhood));
  }

  @RequestMapping(value = "/v1/neighborhood:assign", method = RequestMethod.POST)
  public NeighborhoodOuterClass.Neighborhood assignNeighborhoodToCity(
      @RequestBody NeighborhoodOuterClass.AssignNeighborhoodToCityRequest request)
      throws Exception {
    Neighborhood neighborhood =
        neighborhoodService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    City city =
        cityService.getById(request.getCityId()).orElseThrow(ResourceNotFoundException::new);
    neighborhood.setCity(city);
    return NeighborhoodConverter.toProto(neighborhoodService.save(neighborhood));
  }
}
