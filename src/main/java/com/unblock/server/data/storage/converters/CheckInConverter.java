package com.unblock.server.data.storage.converters;

import com.unblock.proto.CheckInOuterClass;
import com.unblock.server.data.storage.CheckIn;

public class CheckInConverter {

  public static CheckInOuterClass.CheckIn toProto(CheckIn checkIn) {
    return CheckInOuterClass.CheckIn.newBuilder()
        .setCheckinId(checkIn.getId())
        .setAttractionId(checkIn.getAttraction().getId())
        .setCreatedTime(checkIn.getCreatedTime())
        .build();
  }
}
