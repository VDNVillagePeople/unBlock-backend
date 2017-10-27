package com.unblock.server.data.storage;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Block")
public class Block {

  public Block() {}

  public Block(String id) {
    this.id = Integer.parseInt(id);
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String title;

  @ManyToOne
  @JoinColumn(name="neighborhood_id") private Neighborhood neighborhood;

  @OneToMany(mappedBy="block", cascade = {CascadeType.ALL}, orphanRemoval = true)
  private List<Point> points;

  @OneToMany(mappedBy="block", cascade = {CascadeType.ALL})
  private Set<Attraction> attractions;

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

  public List<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
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
        + neighborhood.getId()
        + ", points="
        + points
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Block block = (Block) o;

    if (id != block.id) return false;
    if (title != null ? !title.equals(block.title) : block.title != null) return false;
    if (neighborhood != null ? !(neighborhood.getId() == block.neighborhood.getId()) : block.neighborhood != null) return false;
    if (points != null ? !points.equals(block.points) : block.points != null) return false;
    return attractions != null ? attractions.equals(block.attractions) : block.attractions == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (neighborhood != null ? neighborhood.getId() : 0);
    result = 31 * result + (points != null ? points.hashCode() : 0);
    result = 31 * result + (attractions != null ? attractions.hashCode() : 0);
    return result;
  }
}
