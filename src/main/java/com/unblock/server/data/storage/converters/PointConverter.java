package com.unblock.server.data.storage.converters;

import com.unblock.proto.BoundsOuterClass.Bounds;
import com.unblock.server.data.storage.Point;

public class PointConverter {

    public static Bounds.Point toProto(Point point) {
        return Bounds.Point.newBuilder().setX(point.getX()).setY(point.getY()).build();
    }

    public static Point toJava(Bounds.Point point) {
        return new Point(point.getX(), point.getY());
    }
}
