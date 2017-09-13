package com.unblock.server.data.storage;

import javax.persistence.*;

@Entity
@Table(name = "Point")
public class Point {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private float x;

  private float y;

  private int order;

  @ManyToOne private Block block;

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public Block getBlock() {
    return block;
  }

  public void setBlock(Block block) {
    this.block = block;
  }

  @Override
  public String toString() {
    return "Point{"
        + "id="
        + id
        + ", x="
        + x
        + ", y="
        + y
        + ", order="
        + order
        + ", block="
        + block
        + '}';
  }
}
