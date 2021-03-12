package tech.przybysz.pms.locationsservice.service.dto;

import tech.przybysz.pms.locationsservice.domain.enumeration.AreaUnit;

import java.time.LocalDateTime;

public class AreaPlaceDTO {
  private Long id;
  private String name;
  private String description;
  private LocalDateTime created;
  private LocalDateTime modified;
  private Double area;
  private AreaUnit unit;
  private Boolean paidAdmission;
  private Boolean petsAllowed;
  private Long typeId;
  private Long spotId;

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

  public Double getArea() {
    return area;
  }

  public void setArea(Double area) {
    this.area = area;
  }

  public AreaUnit getUnit() {
    return unit;
  }

  public void setUnit(AreaUnit unit) {
    this.unit = unit;
  }

  public Boolean getPaidAdmission() {
    return paidAdmission;
  }

  public void setPaidAdmission(Boolean paidAdmission) {
    this.paidAdmission = paidAdmission;
  }

  public Boolean getPetsAllowed() {
    return petsAllowed;
  }

  public void setPetsAllowed(Boolean petsAllowed) {
    this.petsAllowed = petsAllowed;
  }

  public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public Long getSpotId() {
    return spotId;
  }

  public void setSpotId(Long spotId) {
    this.spotId = spotId;
  }

  @Override
  public String toString() {
    return "AreaPlaceDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", created=" + created +
        ", modified=" + modified +
        ", area=" + area +
        ", unit=" + unit +
        ", paidAdmission=" + paidAdmission +
        ", petsAllowed=" + petsAllowed +
        ", typeId=" + typeId +
        ", spotId=" + spotId +
        '}';
  }
}
