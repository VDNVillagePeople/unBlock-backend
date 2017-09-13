package com.unblock.server.controllers;

import com.unblock.server.data.storage.converters.NeighborhoodConverter;
import com.unblock.server.exception.NeighborhoodNotFoundException;
import com.unblock.server.services.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class NeighborhoodController {

  @Autowired private NeighborhoodService neighborhoodService;

  @RequestMapping(value = "/neighborhood/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public com.unblock.proto.Neighborhood getNeighborhood(@PathVariable String id) throws Exception {
    return NeighborhoodConverter.toProto(
        neighborhoodService
            .getById(Integer.parseInt(id, 10))
            .orElseThrow(NeighborhoodNotFoundException::new));
  }
}
