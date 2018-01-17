package com.unblock.server.data.storage;

import com.unblock.proto.NeighborhoodOuterClass.NeighborhoodStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
  @JoinColumn(name = "city_id")
  private City city;

  private String name;

  @OneToMany(
    mappedBy = "neighborhood",
    cascade = {CascadeType.ALL},
    orphanRemoval = true
  )
  private Set<Block> blocks;

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
    this.blocks = blocks;
  }
}
