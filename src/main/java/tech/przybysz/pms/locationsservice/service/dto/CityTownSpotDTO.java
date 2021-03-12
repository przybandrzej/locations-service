package tech.przybysz.pms.locationsservice.service.dto;

import java.math.BigDecimal;

public class CityTownSpotDTO {
  private Long id;
  private String name;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private Long parentId;

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

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  @Override
  public String toString() {
    return "CityTownSpotDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", parentId=" + parentId +
        '}';
  }
}
