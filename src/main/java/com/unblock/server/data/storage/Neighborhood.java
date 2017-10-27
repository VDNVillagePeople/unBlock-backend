package com.unblock.server.data.storage;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Neighborhood")
public class Neighborhood {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String title;

  @OneToMany(
    mappedBy = "neighborhood",
    cascade = {CascadeType.ALL},
    orphanRemoval = true
  )
  private Set<Block> blocks;

  @OneToMany(
    mappedBy = "neighborhood",
    cascade = {CascadeType.ALL},
    orphanRemoval = true
  )
  private Set<Attraction> attractions;

  private String image;

  public Neighborhood() {}

  public Neighborhood(String id) {
    this.id = Integer.parseInt(id);
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

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

  public Set<Attraction> getAttractions() {
    return attractions;
  }

  public void setAttractions(Set<Attraction> attractions) {
    this.attractions = attractions;
  }

  public Set<Block> getBlocks() {
    return blocks;
  }

  public void setBlocks(Set<Block> blocks) {
    this.blocks = blocks;
  }

  @Override
  public String toString() {
    return "Neighborhood{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", blocks="
        + blocks
        + ", attractions="
        + attractions
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Neighborhood that = (Neighborhood) o;

    if (id != that.id) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (blocks != null ? !blocks.equals(that.blocks) : that.blocks != null) return false;
    if (attractions != null ? !attractions.equals(that.attractions) : that.attractions != null) return false;
    return image != null ? image.equals(that.image) : that.image == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (blocks != null ? blocks.hashCode() : 0);
    result = 31 * result + (attractions != null ? attractions.hashCode() : 0);
    result = 31 * result + (image != null ? image.hashCode() : 0);
    return result;
  }
}
