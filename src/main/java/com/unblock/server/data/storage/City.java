package com.unblock.server.data.storage;

import com.unblock.proto.CityOuterClass.CityStatus;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "City")
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Enumerated(EnumType.STRING)
  private CityStatus status;

  private String name;

  @OneToMany(
    mappedBy = "city",
    cascade = {CascadeType.ALL},
    orphanRemoval = true
  )
  private Set<Neighborhood> neighborhoods;

  public City() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public Set<Neighborhood> getNeighborhoods() {
    return neighborhoods;
  }

  public void setNeighborhoods(Set<Neighborhood> neighborhoods) {
    this.neighborhoods = neighborhoods;
  }
}
