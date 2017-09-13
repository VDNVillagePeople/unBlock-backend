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

  @OneToMany private Set<Block> blocks;

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

  public Set<Block> getBlocks() {
    return blocks;
  }

  public void setBlocks(Set<Block> blocks) {
    this.blocks = blocks;
  }

  @Override
  public String toString() {
    return "Neighborhood{" + "id=" + id + ", title='" + title + '\'' + ", blocks=" + blocks + '}';
  }
}
