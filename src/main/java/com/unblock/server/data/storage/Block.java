package com.unblock.server.data.storage;

import com.unblock.proto.BlockOuterClass.BlockStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Block")
public class Block {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

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

  public Block() {}

  public Block(String id) {
    this.setId(id);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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
