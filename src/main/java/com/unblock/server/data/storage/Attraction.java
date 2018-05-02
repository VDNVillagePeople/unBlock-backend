package com.unblock.server.data.storage;

import com.unblock.proto.AttractionOuterClass.AttractionStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Attraction")
public class Attraction {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Enumerated(EnumType.STRING)
  private AttractionStatus status;

  @ManyToOne
  @JoinColumn(name = "block_id")
  private Block block;

  private String name;

  private String description;

  private float x;

  private float y;

  private String googlePlaceId;

  public Attraction() {}

  public Attraction(String id) {
    this.setId(id);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AttractionStatus getStatus() {
    return status;
  }

  public void setStatus(AttractionStatus status) {
    this.status = status;
  }

  public Block getBlock() {
    return block;
  }

  public void setBlock(Block block) {
    this.block = block;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public String getGooglePlaceId() {
    return googlePlaceId;
  }

  public void setGooglePlaceId(String googlePlaceId) {
    this.googlePlaceId = googlePlaceId;
  }
}
