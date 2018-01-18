package com.unblock.server.controllers;

import com.unblock.proto.CityOuterClass;
import com.unblock.proto.CityOuterClass.CityStatus;
import com.unblock.server.data.storage.City;
import com.unblock.server.data.storage.converters.CityConverter;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class CityController {

  @Autowired private CityService cityService;

  @RequestMapping(value = "/v1/city", method = RequestMethod.POST)
  public CityOuterClass.City createCity(@RequestBody CityOuterClass.CreateCityRequest request) {
    City city = new City();
    city.setName(request.getInfo().getName());
    city.setStatus(CityStatus.CITY_LIVE);
    return CityConverter.toProto(cityService.create(city));
  }

  @RequestMapping(value = "/v1/cities", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public CityOuterClass.ListCitiesResponse listCities() throws Exception {
    return CityOuterClass.ListCitiesResponse.newBuilder()
            .addAllCities(
                cityService
                    .listAll()
                    .stream()
                    .map(CityConverter::toProto)
                    .collect(Collectors.toList()))
            .build();
  }

  @RequestMapping(value = "/v1/city/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public CityOuterClass.City getCity(@PathVariable String id) throws Exception {
    City city = cityService.getById(id).orElseThrow(ResourceNotFoundException::new);
    return CityConverter.toProto(city);
  }

  @RequestMapping(value = "/v1/city:info", method = RequestMethod.PATCH)
  public CityOuterClass.City updateCityInfo(
      @RequestBody CityOuterClass.UpdateCityInfoRequest request) throws Exception {
    City city = cityService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    city.setName(request.getInfo().getName());
    return CityConverter.toProto(cityService.save(city));
  }

  @RequestMapping(value = "/v1/city:status", method = RequestMethod.PATCH)
  public CityOuterClass.City updateCityStatus(
      @RequestBody CityOuterClass.UpdateCityStatusRequest request) throws Exception {
    City city = cityService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    city.setStatus(request.getStatus());
    return CityConverter.toProto(cityService.save(city));
  }
}
