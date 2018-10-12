package com.unblock.server.data.storage;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Point")
public class Point {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private float x;
  private float y;
  private int orderIndex;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "block_id")
  private Block block;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "city_id")
  private City city;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "neighborhood_id")
  private Neighborhood neighborhood;

  public Point() {}

  public Point(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public int getOrderIndex() {
    return orderIndex;
  }

  public void setOrderIndex(int orderIndex) {
    this.orderIndex = orderIndex;
  }

  public Block getBlock() {
    return block;
  }

  public void setBlock(Block block) {
    this.block = block;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public Neighborhood getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }
}
