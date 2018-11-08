package com.unblock.server.data.storage;

import com.unblock.proto.AttractionOuterClass.AttractionStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "attraction_id")
  private Attraction attraction;

  private String userId;

  private long createdTime;

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

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(long createdTime) {
    this.createdTime = createdTime;
  }
}
