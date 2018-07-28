package com.unblock.server.data.storage;

import com.unblock.proto.CityOuterClass.CityStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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
  private Set<Neighborhood> neighborhoods = new HashSet();

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
    return imageFilename == null ? "" : imageFilename;
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
}
