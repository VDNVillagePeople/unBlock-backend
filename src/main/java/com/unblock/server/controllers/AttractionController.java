package com.unblock.server.controllers;

import com.unblock.proto.AttractionOuterClass;
import com.unblock.proto.AttractionOuterClass.AttractionStatus;
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

  @RequestMapping(value = "/v1/attraction", method = RequestMethod.POST)
  public AttractionOuterClass.Attraction createAttraction(
      @RequestBody AttractionOuterClass.CreateAttractionRequest request) throws Exception {
    Attraction newAttraction = new Attraction();
    newAttraction.setBlock(new Block(request.getBlockId()));
    newAttraction.setStatus(AttractionStatus.ATTRACTION_LIVE);
    newAttraction.setName(request.getInfo().getName());
    newAttraction.setX(request.getInfo().getLocation().getX());
    newAttraction.setY(request.getInfo().getLocation().getY());
    newAttraction.setDescription(request.getInfo().getDescription());
    return AttractionConverter.toProto(attractionService.create(newAttraction));
  }

  @RequestMapping(value = "attraction/info", method = RequestMethod.POST)
  public AttractionOuterClass.Attraction updateAttractionInfo(
      @RequestBody AttractionOuterClass.UpdateAttractionInfoRequest request) throws Exception {

    Attraction attraction =
        attractionService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);

    attraction.setName(request.getInfo().getName());
    attraction.setDescription(request.getInfo().getDescription());

    attractionService.update(attraction);

    return AttractionConverter.toProto(attraction);
  }

  @RequestMapping(value = "attraction/location", method = RequestMethod.POST)
  public AttractionOuterClass.Attraction updateAttractionLocation(
      @RequestBody AttractionOuterClass.UpdateAttractionLocationRequest request) throws Exception {

    Attraction attraction =
        attractionService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);

    attraction.setX(request.getLocation().getX());
    attraction.setY(request.getLocation().getY());

    attractionService.update(attraction);

    return AttractionConverter.toProto(attraction);
  }
}
