package com.unblock.server.controllers;

import com.unblock.proto.BlockOuterClass;
import com.unblock.server.data.storage.Block;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.Point;
import com.unblock.server.data.storage.converters.BlockConverter;
import com.unblock.server.data.storage.converters.PointConverter;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BlockController {

  @Autowired private BlockService blockService;

  @RequestMapping(value = "block/info", method = RequestMethod.POST)
  public BlockOuterClass.Block updateBlockInfo(
      @RequestBody BlockOuterClass.UpdateBlockInfoRequest request) throws Exception {

    Block block =
        blockService
            .getById(Integer.parseInt(request.getId()))
            .orElseThrow(ResourceNotFoundException::new);

    block.setTitle(request.getName());
    block.setNeighborhood(new Neighborhood(request.getNeighborhoodId()));

    blockService.update(block);

    return BlockConverter.toProto(block);
  }

  @RequestMapping(value = "block/bounds", method = RequestMethod.POST)
  public BlockOuterClass.Block updateBlockBounds(
      @RequestBody BlockOuterClass.UpdateBlockBoundsRequest request) throws Exception {

    Block block =
        blockService
            .getById(Integer.parseInt(request.getId()))
            .orElseThrow(ResourceNotFoundException::new);

    List<Point> points =
        request
            .getBounds()
            .getPointsList()
            .stream()
            .map(PointConverter::toJava)
            .collect(Collectors.toList());

    int index = 0;
    for (Point point : points) {
      point.setBlock(block);
      point.setOrderIndex(index++);
    }

    block.setPoints(points);

    System.out.println("Printing block!");
    System.out.println(block);

    blockService.update(block);

    return BlockConverter.toProto(block);
  }

  @RequestMapping(value = "block", method = RequestMethod.POST)
  public BlockOuterClass.Block createBlock(@RequestBody BlockOuterClass.CreateBlockRequest request)
      throws Exception {
    List<Point> points =
        request
            .getBounds()
            .getPointsList()
            .stream()
            .map(PointConverter::toJava)
            .collect(Collectors.toList());

    Block newBlock = new Block();
    int index = 0;
    for (Point point : points) {
      point.setBlock(newBlock);
      point.setOrderIndex(index++);
    }

    newBlock.setTitle(request.getName());
    newBlock.setNeighborhood(new Neighborhood(request.getNeighborhoodId()));
    newBlock.setPoints(points);

    blockService.create(newBlock);

    return BlockConverter.toProto(newBlock);
  }
}
