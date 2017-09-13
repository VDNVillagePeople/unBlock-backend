package com.unblock.server.data.storage;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Block")
public class Block {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String title;

  @ManyToOne private Neighborhood neighborhood;

  @OneToMany private Set<Point> points;

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

  public Neighborhood getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }

  public Set<Point> getPoints() {
    return points;
  }

  public void setPoints(Set<Point> points) {
    this.points = points;
  }

  @Override
  public String toString() {
    return "Block{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", neighborhood="
        + neighborhood
        + ", points="
        + points
        + '}';
  }
}
