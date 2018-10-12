package com.unblock.server.controllers;

import com.unblock.proto.BlockOuterClass;
import com.unblock.proto.BlockOuterClass.BlockStatus;
import com.unblock.proto.BoundsOuterClass.Bounds;
import com.unblock.proto.NeighborhoodOuterClass;
import com.unblock.server.data.storage.Block;
import com.unblock.server.data.storage.City;
import com.unblock.server.data.storage.Neighborhood;
import com.unblock.server.data.storage.Point;
import com.unblock.server.data.storage.converters.AttractionConverter;
import com.unblock.server.data.storage.converters.BlockConverter;
import com.unblock.server.data.storage.converters.NeighborhoodConverter;
import com.unblock.server.data.storage.converters.PointConverter;
import com.unblock.server.exception.ResourceNotFoundException;
import com.unblock.server.services.BlockService;
import com.unblock.server.services.NeighborhoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BlockController {

  @Autowired private BlockService blockService;
  @Autowired private NeighborhoodService neighborhoodService;

  @Autowired private BlockConverter blockConverter;

  @RequestMapping(value = "/v1/block", method = RequestMethod.POST)
  public BlockOuterClass.Block createBlock(@RequestBody BlockOuterClass.CreateBlockRequest request)
      throws Exception {
    Neighborhood neighborhood =
        neighborhoodService
            .getById(request.getNeighborhoodId())
            .orElseThrow(ResourceNotFoundException::new);
    Block newBlock = new Block();
    newBlock.setNeighborhood(neighborhood);
    setPoints(newBlock, request.getInfo().getBounds());
    newBlock.setName(request.getInfo().getName());
    newBlock.setStatus(BlockStatus.BLOCK_LIVE);
    newBlock.setNeighborhood(new Neighborhood(request.getNeighborhoodId()));
    return blockConverter.toProto(blockService.create(newBlock));
  }

  @RequestMapping(value = "/v1/blocks", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public BlockOuterClass.ListBlocksResponse listAllBlocks() throws Exception {
    return BlockOuterClass.ListBlocksResponse.newBuilder()
        .addAllBlocks(
            blockService
                .listAll()
                .stream()
                .map(blockConverter::toProto)
                .collect(Collectors.toList()))
        .build();
  }

  @RequestMapping(value = "/v1/neighborhood/{neighborhoodId}/blocks", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public BlockOuterClass.ListBlocksResponse listBlocksByNeighborhood(
      @PathVariable String neighborhoodId) throws Exception {
    return BlockOuterClass.ListBlocksResponse.newBuilder()
        .addAllBlocks(
            blockService
                .listByNeighborhood(neighborhoodId)
                .stream()
                .map(blockConverter::toProto)
                .collect(Collectors.toList()))
        .build();
  }

  @RequestMapping(value = "/v1/block/{id}", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public BlockOuterClass.Block getBlock(@PathVariable String id) throws Exception {
    Block block = blockService.getById(id).orElseThrow(ResourceNotFoundException::new);
    return blockConverter.toProto(block);
  }

  @RequestMapping(value = "/v1/block:info", method = RequestMethod.PATCH)
  public BlockOuterClass.Block updateBlockInfo(
      @RequestBody BlockOuterClass.UpdateBlockInfoRequest request) throws Exception {
    Block block = blockService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    block.setName(request.getInfo().getName());
    return blockConverter.toProto(blockService.save(block));
  }

  @RequestMapping(value = "/v1/block:status", method = RequestMethod.PATCH)
  public BlockOuterClass.Block updateBlockStatus(
      @RequestBody BlockOuterClass.UpdateBlockStatusRequest request)
      throws Exception {
    Block block =
        blockService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    block.setStatus(request.getStatus());
    return blockConverter.toProto(blockService.save(block));
  }

  @RequestMapping(value = "/v1/block:bounds", method = RequestMethod.PATCH)
  public BlockOuterClass.Block updateBlockBounds(
      @RequestBody BlockOuterClass.UpdateBlockBoundsRequest request) throws Exception {
    Block block = blockService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    setPoints(block, request.getUpdate().getBounds());
    return blockConverter.toProto(blockService.save(block));
  }

  @RequestMapping(value = "/v1/block:assign", method = RequestMethod.PATCH)
  public BlockOuterClass.Block assignBlockToNeighborhood(
      @RequestBody BlockOuterClass.AssignBlockToNeighborhoodRequest request) throws Exception {
    Block block = blockService.getById(request.getId()).orElseThrow(ResourceNotFoundException::new);
    Neighborhood neighborhood =
        neighborhoodService
            .getById(request.getNeighborhoodId())
            .orElseThrow(ResourceNotFoundException::new);
    block.setNeighborhood(neighborhood);
    return blockConverter.toProto(blockService.save(block));
  }

  private Block setPoints(Block block, Bounds bounds) {
    List<Point> points =
        bounds.getPointsList().stream().map(PointConverter::toJava).collect(Collectors.toList());
    int index = 0;
    for (Point point : points) {
      point.setBlock(block);
      point.setOrderIndex(index++);
    }
    block.setPoints(points);
    return block;
  }
}
