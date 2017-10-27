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
  private int orderIndex;
  @ManyToOne
  @JoinColumn(name="block_id") private Block block;

  public Point() {}

  public Point(float x, float y) {
    this.x = x;
    this.y = y;
  }

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

  @Override
  public String toString() {
    return "Point{"
        + "id="
        + id
        + ", x="
        + x
        + ", y="
        + y
        + ", orderIndex="
        + orderIndex
        + ", block="
        + block.getId()
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Point point = (Point) o;

    if (id != point.id) return false;
    if (Float.compare(point.x, x) != 0) return false;
    if (Float.compare(point.y, y) != 0) return false;
    if (orderIndex != point.orderIndex) return false;
    return block != null ? block.getId() == point.block.getId() : point.block == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (x != +0.0f ? Float.floatToIntBits(x) : 0);
    result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
    result = 31 * result + orderIndex;
    result = 31 * result + (block != null ? block.getId() : 0);
    return result;
  }
}
