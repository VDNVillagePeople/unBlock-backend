package com.unblock.server.data.storage.converters;

import com.unblock.proto.BlockOuterClass;
import com.unblock.proto.BoundsOuterClass;
import com.unblock.server.data.storage.Block;
import com.unblock.server.data.storage.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public class BlockConverter {

  @Autowired
  private AttractionConverter attractionConverter;

  public BlockOuterClass.Block toProto(Block block) {
    return BlockOuterClass.Block.newBuilder()
        .setId(block.getId())
        .setStatus(block.getStatus())
        .setNeighborhoodId(block.getNeighborhood().getId())
        .setName(block.getName())
        .setBounds(
            BoundsOuterClass.Bounds.newBuilder()
                .addAllPoints(
                    block
                        .getPoints()
                        .stream()
                        .sorted(Comparator.comparingInt(Point::getOrderIndex))
                        .map(PointConverter::toProto)
                        .collect(Collectors.toList())))
        .addAllAttractions(
            block
                .getAttractions()
                .stream()
                .map(attraction -> attractionConverter.toProto(attraction))
                .collect(Collectors.toList()))
        .build();
  }
}
