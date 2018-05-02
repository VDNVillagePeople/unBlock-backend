package com.unblock.server.data.storage.converters;

import com.unblock.proto.CheckInOuterClass;
import com.unblock.server.data.storage.CheckIn;

public class CheckInConverter {

  public static CheckInOuterClass.CheckIn toProto(CheckIn checkIn) {
    return CheckInOuterClass.CheckIn.newBuilder()
        .setAttractionId(checkIn.getAttraction().getId())
        .setUserId(checkIn.getUser().getId())
        .build();
  }
}
