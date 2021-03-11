package tech.przybysz.pms.locationsservice.service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PointLocationDTO {
  private Long id;
  private String name;
  private String description;
  private LocalDateTime created;
  private LocalDateTime modified;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private Long typeId;
  private Long areaPlaceId;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public Long getAreaPlaceId() {
    return areaPlaceId;
  }

  public void setAreaPlaceId(Long areaPlaceId) {
    this.areaPlaceId = areaPlaceId;
  }

  @Override
  public String toString() {
    return "PointLocationDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", created=" + created +
        ", modified=" + modified +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", typeId=" + typeId +
        ", areaPlaceId=" + areaPlaceId +
        '}';
  }
}
