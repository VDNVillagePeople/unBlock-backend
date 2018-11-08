package com.unblock.server.controllers;

import com.unblock.proto.CheckInOuterClass;
import com.unblock.proto.CheckInOuterClass.ListCheckInsForUserResponse;
import com.unblock.server.data.storage.Attraction;
import com.unblock.server.data.storage.CheckIn;
import com.unblock.server.data.storage.converters.CheckInConverter;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.services.AttractionService;
import com.unblock.server.services.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CheckInController {

  @Autowired private CheckInService checkInService;
  @Autowired private AttractionService attractionService;

  @RequestMapping(value = "/v1/checkIn", method = RequestMethod.POST)
  public CheckInOuterClass.CheckIn createCheckIn(
      @RequestBody CheckInOuterClass.CreateCheckInRequest request, Principal principal)
      throws Exception {
    Attraction attraction =
        attractionService
            .getById(request.getAttractionId())
            .orElseThrow(ResourceNotFoundException::new);
    CheckIn checkIn = new CheckIn();
    checkIn.setAttraction(attraction);
    checkIn.setUserId(principal.getName());
    checkIn.setCreatedTime(System.currentTimeMillis());
    return CheckInConverter.toProto(checkInService.create(checkIn));
  }

  @RequestMapping(value = "/v1/checkIns", method = RequestMethod.GET)
  public ListCheckInsForUserResponse getCheckIns(Principal principal) throws Exception {
    List<CheckIn> checkIns = checkInService.listByUser(principal.getName());
    return ListCheckInsForUserResponse.newBuilder()
        .addAllCheckIns(
            checkIns.stream().map(CheckInConverter::toProto).collect(Collectors.toList()))
        .build();
  }
}
