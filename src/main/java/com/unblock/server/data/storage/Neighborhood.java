package com.unblock.server.data.storage;

import com.unblock.proto.NeighborhoodOuterClass.NeighborhoodStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Neighborhood")
public class Neighborhood {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Enumerated(EnumType.STRING)
  private NeighborhoodStatus status;

  @ManyToOne
  @Fetch(FetchMode.JOIN)
  @JoinColumn(name = "city_id")
  private City city;

  private String name;

  @OneToMany(
    mappedBy = "neighborhood",
    cascade = {CascadeType.ALL},
    orphanRemoval = true
  )
  @Fetch(FetchMode.JOIN)
  private Set<Block> blocks = new HashSet();

  @OneToMany(
    mappedBy = "neighborhood",
    cascade = {CascadeType.ALL},
    orphanRemoval = true
  )
  @Fetch(FetchMode.JOIN)
  private Set<Point> bounds = new HashSet();

  public Neighborhood() {}

  public Neighborhood(String id) {
    this.setId(id);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public NeighborhoodStatus getStatus() {
    return status;
  }

  public void setStatus(NeighborhoodStatus status) {
    this.status = status;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Block> getBlocks() {
    return blocks;
  }

  public void setBlocks(Set<Block> blocks) {
    this.blocks.clear();
    this.blocks.addAll(blocks);
  }

  public Set<Point> getBounds() {
    return bounds;
  }

  public void setBounds(List<Point> bounds) {
    this.bounds.clear();
    this.bounds.addAll(bounds);
  }
}
