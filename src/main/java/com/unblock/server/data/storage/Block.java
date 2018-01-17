package com.unblock.server.data.storage;

import com.unblock.proto.BlockOuterClass.BlockStatus;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Block")
public class Block {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Enumerated(EnumType.STRING)
  private BlockStatus status;

  @ManyToOne
  @JoinColumn(name = "neighborhood_id")
  private Neighborhood neighborhood;

  private String name;

  @OneToMany(
    mappedBy = "block",
    cascade = {CascadeType.ALL},
    orphanRemoval = true
  )
  private List<Point> points;

  @OneToMany(
    mappedBy = "attraction",
    cascade = {CascadeType.ALL}
  )
  private Set<Attraction> attractions;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BlockStatus getStatus() {
    return status;
  }

  public void setStatus(BlockStatus status) {
    this.status = status;
  }

  public Neighborhood getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
    this.points = points;
  }

  public Set<Attraction> getAttractions() {
    return attractions;
  }

  public void setAttractions(Set<Attraction> attractions) {
    this.attractions = attractions;
  }
}
