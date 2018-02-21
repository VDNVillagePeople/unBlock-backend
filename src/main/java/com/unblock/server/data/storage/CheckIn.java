package com.unblock.server.data.storage;

import com.unblock.proto.AttractionOuterClass.AttractionStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "CheckIn")
public class CheckIn {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne
  @JoinColumn(name = "attraction_id")
  private Attraction attraction;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public CheckIn() {}

  public CheckIn(String id) {
    this.setId(id);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Attraction getAttraction() {
    return attraction;
  }

  public void setAttraction(Attraction attraction) {
    this.attraction = attraction;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
