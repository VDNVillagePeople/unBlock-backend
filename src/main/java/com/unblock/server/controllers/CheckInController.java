package com.unblock.server.controllers;

import com.unblock.proto.CheckInOuterClass;
import com.unblock.server.data.storage.*;
import com.unblock.server.data.storage.converters.CheckInConverter;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckInController {

  @Autowired private CheckInService checkInService;
  @Autowired private UserService userService;
  @Autowired private AttractionService attractionService;

  @RequestMapping(value = "/v1/checkIn", method = RequestMethod.POST)
  public CheckInOuterClass.CheckIn createCheckIn(
      @RequestBody CheckInOuterClass.CreateCheckInRequest request) throws Exception {
    User user =
        userService.getById(request.getUserId()).orElseThrow(ResourceNotFoundException::new);
    Attraction attraction =
        attractionService.getById(request.getUserId()).orElseThrow(ResourceNotFoundException::new);
    CheckIn checkIn = new CheckIn();
    checkIn.setAttraction(attraction);
    checkIn.setUser(user);
    return CheckInConverter.toProto(checkInService.create(checkIn));
  }

  @RequestMapping(value = "/v1/checkIn", method = RequestMethod.DELETE)
  public CheckInOuterClass.CheckIn deleteCheckIn(
      @RequestBody CheckInOuterClass.CreateCheckInRequest request) throws Exception {
    User user =
        userService.getById(request.getUserId()).orElseThrow(ResourceNotFoundException::new);
    Attraction attraction =
        attractionService.getById(request.getUserId()).orElseThrow(ResourceNotFoundException::new);
    CheckIn checkIn = new CheckIn();
    checkIn.setAttraction(attraction);
    checkIn.setUser(user);
    return CheckInConverter.toProto(checkInService.create(checkIn));
  }
}
