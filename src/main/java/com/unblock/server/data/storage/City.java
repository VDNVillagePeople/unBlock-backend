package com.unblock.server.data.storage;

import com.unblock.proto.CityOuterClass.CityStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "City")
public class City {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Enumerated(EnumType.STRING)
  private CityStatus status;

  private String name;

  private String imageFilename = "";

  @OneToMany(
    mappedBy = "city",
    cascade = {CascadeType.ALL},
    orphanRemoval = true
  )
  @Fetch(FetchMode.JOIN)
  private Set<Neighborhood> neighborhoods = new HashSet();

  private float x;

  private float y;

  @OneToMany(
      mappedBy = "city",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
  )
  @Fetch(FetchMode.JOIN)
  private Set<Point> bounds = new HashSet();

  public City() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CityStatus getStatus() {
    return status;
  }

  public void setStatus(CityStatus status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImageFilename() {
    return imageFilename;
  }

  public void setImageFilename(String imageFilename) {
    this.imageFilename = imageFilename;
  }

  public Set<Neighborhood> getNeighborhoods() {
    return neighborhoods;
  }

  public void setNeighborhoods(Set<Neighborhood> neighborhoods) {
    this.neighborhoods.clear();
    this.neighborhoods.addAll(neighborhoods);
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

  public Set<Point> getBounds() {
    return bounds;
  }

  public void setBounds(List<Point> bounds) {
    this.bounds.clear();
    this.bounds.addAll(bounds);
  }
}
