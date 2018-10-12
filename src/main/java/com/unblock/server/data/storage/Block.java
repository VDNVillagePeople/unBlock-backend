package com.unblock.server.data.storage;

import com.unblock.proto.BlockOuterClass.BlockStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
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
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "neighborhood_id")
  private Neighborhood neighborhood;

  private String name;

  @OneToMany(
    fetch = FetchType.EAGER,
    mappedBy = "block",
    cascade = {CascadeType.ALL},
    orphanRemoval = true
  )
  @Fetch(FetchMode.JOIN)
  private Set<Point> points = new HashSet();

  @OneToMany(
    fetch = FetchType.EAGER,
    mappedBy = "block",
    cascade = {CascadeType.ALL}
  )
  @Fetch(FetchMode.JOIN)
  private Set<Attraction> attractions = new HashSet();

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

  public Set<Point> getPoints() {
    return points;
  }

  public void setPoints(List<Point> points) {
    this.points.clear();
    this.points.addAll(points);
  }

  public Set<Attraction> getAttractions() {
    return attractions;
  }

  public void setAttractions(Set<Attraction> attractions) {
    this.attractions.clear();
    this.attractions.addAll(attractions);
  }
}
