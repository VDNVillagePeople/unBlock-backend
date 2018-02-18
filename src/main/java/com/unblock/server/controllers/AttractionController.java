package com.unblock.server.controllers;

import com.unblock.proto.AttractionOuterClass;
import com.unblock.proto.AttractionOuterClass.AttractionStatus;
import com.unblock.server.data.storage.Attraction;
import com.unblock.server.data.storage.Block;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.converters.AttractionConverter;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.services.AttractionService;
import com.unblock.server.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttractionController {

  @Autowired private AttractionService attractionService;
  @Autowired private BlockService blockService;

  @RequestMapping(value = "/v1/attraction", method = RequestMethod.POST)
  public AttractionOuterClass.Attraction createAttraction(
      @RequestBody AttractionOuterClass.CreateAttractionRequest request) throws Exception {
    Block block = blockService.getById(request.getBlockId()).orElseThrow(ResourceNotFoundException::new);
    Attraction newAttraction = new Attraction();
    newAttraction.setBlock(block);
    newAttraction.setStatus(AttractionStatus.ATTRACTION_LIVE);
    newAttraction.setName(request.getInfo().getName());
    newAttraction.setX(request.getInfo().getLocation().getX());
    newAttraction.setY(request.getInfo().getLocation().getY());
    newAttraction.setDescription(request.getInfo().getDescription());
    return AttractionConverter.toProto(attractionService.create(newAttraction));
  }

  @RequestMapping(value = "/v1/attraction:info", method = RequestMethod.PATCH)
  public AttractionOuterClass.Attraction updateAttractionInfo(
      @RequestBody AttractionOuterClass.UpdateAttractionInfoRequest request) throws Exception {
    Attraction attraction =
        attractionService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    attraction.setName(request.getInfo().getName());
    attraction.setDescription(request.getInfo().getDescription());
    return AttractionConverter.toProto(attractionService.save(attraction));
  }

  @RequestMapping(value = "/v1/attraction:location", method = RequestMethod.PATCH)
  public AttractionOuterClass.Attraction updateAttractionLocation(
      @RequestBody AttractionOuterClass.UpdateAttractionLocationRequest request) throws Exception {
    Attraction attraction =
        attractionService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    attraction.setX(request.getLocation().getX());
    attraction.setY(request.getLocation().getY());
    return AttractionConverter.toProto(attractionService.save(attraction));
  }

  @RequestMapping(value = "/v1/attraction:status", method = RequestMethod.PATCH)
  public AttractionOuterClass.Attraction updateAttractionStatus(
      @RequestBody AttractionOuterClass.UpdateAttractionStatusRequest request) throws Exception {
    Attraction attraction =
        attractionService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    attraction.setStatus(request.getStatus());
    return AttractionConverter.toProto(attractionService.save(attraction));
  }

  @RequestMapping(value = "/v1/attraction:assign", method = RequestMethod.PATCH)
  public AttractionOuterClass.Attraction assignAttractionToBlock(
      @RequestBody AttractionOuterClass.AssignAttractionToBlockRequest request) throws Exception {
    Attraction attraction =
        attractionService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    Block block = blockService.getById(request.getBlockId()).orElseThrow(ResourceNotFoundException::new);
    attraction.setBlock(block);
    return AttractionConverter.toProto(attractionService.save(attraction));
  }
}
