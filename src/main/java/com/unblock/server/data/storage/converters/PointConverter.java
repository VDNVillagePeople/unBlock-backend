package com.unblock.server.data.storage.converters;

import com.unblock.proto.PointOuterClass;
import com.unblock.server.data.storage.Point;

public class PointConverter {

  public static PointOuterClass.Point toProto(Point point) {
    return PointOuterClass.Point.newBuilder().setX(point.getX()).setY(point.getY()).build();
  }

  public static Point toJava(PointOuterClass.Point point) {
    return new Point(point.getX(), point.getY());
  }
}
