package com.unblock.server.data.storage;

import com.unblock.proto.UserOuterClass.UserLevel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String username;

  private String password;

  private String email;

  @Enumerated(EnumType.STRING)
  private UserLevel level;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserLevel getLevel() {
    return level;
  }

  public void setLevel(UserLevel level) {
    this.level = level;
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", email='"
        + email
        + '\''
        + ", level='"
        + level
        + '\''
        + '}';
  }
}
