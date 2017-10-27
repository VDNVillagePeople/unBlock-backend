package com.unblock.server.data.storage;

import javax.persistence.*;

@Entity
@Table(name = "Attraction")
public class Attraction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String title;

  private float x;

  private float y;

  @ManyToOne
  @JoinColumn(name = "neighborhood_id")
  private Neighborhood neighborhood;

  @ManyToOne
  @JoinColumn(name = "block_id")
  private Block block;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public Neighborhood getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }

  public Block getBlock() {
    return block;
  }

  public void setBlock(Block block) {
    this.block = block;
  }

  @Override
  public String toString() {
    return "Attraction{"
        + "id="
        + id
        + ", title="
        + title
        + ", x="
        + x
        + ", y="
        + y
        + ", blockId="
        + block.getId()
        + ", neighborhoodId="
        + neighborhood.getId()
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Attraction that = (Attraction) o;

    if (id != that.id) return false;
    if (!title.equals(that.title)) return false;
    if (Float.compare(that.x, x) != 0) return false;
    if (Float.compare(that.y, y) != 0) return false;
    if (neighborhood != null ? neighborhood.getId() != that.neighborhood.getId() : that.neighborhood != null) return false;
    return block != null ? block.getId() == that.block.getId() : that.block == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + title.hashCode();
    result = 31 * result + (x != +0.0f ? Float.floatToIntBits(x) : 0);
    result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
    result = 31 * result + (neighborhood != null ? neighborhood.getId() : 0);
    result = 31 * result + (block != null ? block.getId(): 0);
    return result;
  }
}
