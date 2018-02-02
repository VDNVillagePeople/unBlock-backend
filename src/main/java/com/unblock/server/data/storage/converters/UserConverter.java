package com.unblock.server.data.storage.converters;

import com.unblock.proto.CityOuterClass;
import com.unblock.proto.UserOuterClass;
import com.unblock.server.data.storage.User;

import java.util.stream.Collectors;

public class UserConverter {
  public static UserOuterClass.User toProto(User user) {
    return UserOuterClass.User.newBuilder()
        .setId(user.getId())
        .setUsername(user.getUsername())
        .setEmail(user.getEmail())
        .build();
  }
}
