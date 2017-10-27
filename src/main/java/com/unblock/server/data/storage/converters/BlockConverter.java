package com.unblock.server.data.storage.converters;

import com.unblock.proto.BlockOuterClass;
import com.unblock.proto.BlockOuterClass.Bounds;
import com.unblock.server.data.storage.Block;
import com.unblock.server.data.storage.Point;

import java.util.Comparator;
import java.util.stream.Collectors;

public class BlockConverter {

  public static BlockOuterClass.Block toProto(Block block) {
    return BlockOuterClass.Block.newBuilder()
        .setId(Integer.toString(block.getId()))
        .setName(block.getTitle())
        .setBounds(
            Bounds.newBuilder()
                .addAllPoints(
                    block
                        .getPoints()
                        .stream()
                        .sorted(Comparator.comparingInt(Point::getOrderIndex))
                        .map(PointConverter::toProto)
                        .collect(Collectors.toList())))
        .build();
  }
}
