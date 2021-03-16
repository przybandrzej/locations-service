package tech.przybysz.pms.locationsservice.service.dto;

import tech.przybysz.pms.locationsservice.domain.enumeration.PriceRateSimple;
import tech.przybysz.pms.locationsservice.domain.enumeration.SimpleRate;

import java.time.LocalDateTime;

public class StayPlaceDTO {
  private Long id;
  private String name;
  private String description;
  private LocalDateTime created;
  private LocalDateTime modified;
  private SimpleRate peaceRate;
  private SimpleRate locationRate;
  private PriceRateSimple priceRate;
  private Boolean petsAllowed;
  private Boolean hasCarParking;
  private SimpleRate comfortRate;
  private SimpleRate standardRate;
  private Long typeId;
  private Long addressId;

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

  public SimpleRate getPeaceRate() {
    return peaceRate;
  }

  public void setPeaceRate(SimpleRate peaceRate) {
    this.peaceRate = peaceRate;
  }

  public SimpleRate getLocationRate() {
    return locationRate;
  }

  public void setLocationRate(SimpleRate locationRate) {
    this.locationRate = locationRate;
  }

  public PriceRateSimple getPriceRate() {
    return priceRate;
  }

  public void setPriceRate(PriceRateSimple priceRate) {
    this.priceRate = priceRate;
  }

  public Boolean getPetsAllowed() {
    return petsAllowed;
  }

  public void setPetsAllowed(Boolean petsAllowed) {
    this.petsAllowed = petsAllowed;
  }

  public Boolean gethasCarParking() {
    return hasCarParking;
  }

  public void sethasCarParking(Boolean hasCarParking) {
    this.hasCarParking = hasCarParking;
  }

  public SimpleRate getComfortRate() {
    return comfortRate;
  }

  public void setComfortRate(SimpleRate comfortRate) {
    this.comfortRate = comfortRate;
  }

  public SimpleRate getStandardRate() {
    return standardRate;
  }

  public void setStandardRate(SimpleRate standardRate) {
    this.standardRate = standardRate;
  }

  public Long getTypeId() {
    return typeId;
  }

  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }

  public Long getAddressId() {
    return addressId;
  }

  public void setAddressId(Long addressId) {
    this.addressId = addressId;
  }

  @Override
  public String toString() {
    return "StayPlaceDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", created=" + created +
        ", modified=" + modified +
        ", peaceRate=" + peaceRate +
        ", locationRate=" + locationRate +
        ", priceRate=" + priceRate +
        ", petsAllowed=" + petsAllowed +
        ", hasCarParking=" + hasCarParking +
        ", comfortRate=" + comfortRate +
        ", standardRate=" + standardRate +
        ", typeId=" + typeId +
        ", addressId=" + addressId +
        '}';
  }
}
