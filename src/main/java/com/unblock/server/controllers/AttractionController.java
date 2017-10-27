package com.unblock.server.controllers;

import com.unblock.proto.AttractionOuterClass;
import com.unblock.server.data.storage.Attraction;
import com.unblock.server.data.storage.Block;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.converters.AttractionConverter;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.services.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttractionController {

  @Autowired private AttractionService attractionService;

  @RequestMapping(value = "attraction/info", method = RequestMethod.POST)
  public AttractionOuterClass.Attraction updateAttractionInfo(
      @RequestBody AttractionOuterClass.UpdateAttractionInfoRequest request) throws Exception {

    Attraction attraction =
        attractionService
            .getById(Integer.parseInt(request.getId()))
            .orElseThrow(ResourceNotFoundException::new);

    attraction.setTitle(request.getName());
    attraction.setBlock(new Block(request.getBlockId()));
    attraction.setNeighborhood(new Neighborhood(request.getNeighborhoodId()));

    attractionService.update(attraction);

    return AttractionConverter.toProto(attraction);
  }

  @RequestMapping(value = "attraction/location", method = RequestMethod.POST)
  public AttractionOuterClass.Attraction updateAttractionLocation(
      @RequestBody AttractionOuterClass.UpdateAttractionLocationRequest request) throws Exception {

    Attraction attraction =
        attractionService
            .getById(Integer.parseInt(request.getId()))
            .orElseThrow(ResourceNotFoundException::new);

    attraction.setX(request.getX());
    attraction.setY(request.getY());

    attractionService.update(attraction);

    return AttractionConverter.toProto(attraction);
  }

  @RequestMapping(value = "attraction", method = RequestMethod.POST)
  public AttractionOuterClass.Attraction createAttraction(
      @RequestBody AttractionOuterClass.CreateAttractionRequest request) throws Exception {

    Attraction newAttraction = new Attraction();
    newAttraction.setNeighborhood(new Neighborhood(request.getNeighborhoodId()));
    newAttraction.setBlock(new Block(request.getBlockId()));
    newAttraction.setX(request.getX());
    newAttraction.setY(request.getY());
    newAttraction.setTitle(request.getName());

    attractionService.create(newAttraction);

    return AttractionConverter.toProto(newAttraction);
  }
}
