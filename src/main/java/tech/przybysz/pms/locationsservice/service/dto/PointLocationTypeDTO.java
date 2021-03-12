package tech.przybysz.pms.locationsservice.service.dto;

import java.time.LocalDateTime;

public class PointLocationTypeDTO {
  private Long id;
  private String name;
  private LocalDateTime created;
  private LocalDateTime modified;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getModified() {
    return modified;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  @Override
  public String toString() {
    return "PointLocationTypeDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", created=" + created +
        ", modified=" + modified +
        '}';
  }
}
