package com.unblock.server.data.storage.converters;

import com.unblock.proto.Block.Bounds;
import com.unblock.server.data.storage.Block;
import com.unblock.server.data.storage.Point;

import java.util.stream.Collectors;

public class BlockConverter {

  public static com.unblock.proto.Block toProto(Block block) {
    return com.unblock.proto.Block.newBuilder()
        .setBounds(
            Bounds.newBuilder()
                .addAllPoints(
                    block
                        .getPoints()
                        .stream()
                        .map(BlockConverter::toProto)
                        .collect(Collectors.toList())))
        .build();
  }

  public static Bounds.Point toProto(Point point) {
    return Bounds.Point.newBuilder().setX(point.getX()).setY(point.getY()).build();
  }
}
