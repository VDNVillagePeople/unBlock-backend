package com.unblock.server.controllers;

import com.unblock.proto.NeighborhoodOuterClass;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.converters.NeighborhoodConverter;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.services.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class NeighborhoodController {

  @Autowired private NeighborhoodService neighborhoodService;

  @RequestMapping(value = "neighborhood", method = RequestMethod.POST)
  public NeighborhoodOuterClass.Neighborhood createNeighborHood(
      @RequestBody NeighborhoodOuterClass.CreateNeighborhoodRequest request) {
    Neighborhood neighborhood = new Neighborhood();
    neighborhood.setTitle(request.getName());
    return NeighborhoodConverter.toProto(neighborhoodService.create(neighborhood));
  }

  @RequestMapping(value = "/neighborhoods", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public NeighborhoodOuterClass.ListNeighborhoodResponse listNeighborhoods() throws Exception {
    System.out.println(neighborhoodService.getAll());
    NeighborhoodOuterClass.ListNeighborhoodResponse temp =
        NeighborhoodOuterClass.ListNeighborhoodResponse.newBuilder()
            .addAllNeighborhoods(
                neighborhoodService
                    .getAll()
                    .stream()
                    .map(NeighborhoodConverter::toProto)
                    .collect(Collectors.toList()))
            .build();

    System.out.println(temp);
    return temp;
  }

  @RequestMapping(value = "/neighborhood/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public NeighborhoodOuterClass.Neighborhood getNeighborhood(@PathVariable String id)
      throws Exception {
    Neighborhood neighborhood =
        neighborhoodService
            .getById(Integer.parseInt(id, 10))
            .orElseThrow(ResourceNotFoundException::new);
    System.out.println(neighborhood);
    return NeighborhoodConverter.toProto(neighborhood);
  }
}
