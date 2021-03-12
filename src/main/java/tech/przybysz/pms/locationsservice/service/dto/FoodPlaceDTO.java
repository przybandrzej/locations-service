package tech.przybysz.pms.locationsservice.service.dto;

import tech.przybysz.pms.locationsservice.domain.enumeration.PriceRateSimple;
import tech.przybysz.pms.locationsservice.domain.enumeration.SimpleRate;

import java.time.LocalDateTime;

public class FoodPlaceDTO {
  private Long id;
  private String name;
  private String description;
  private LocalDateTime created;
  private LocalDateTime modified;
  private SimpleRate foodRate;
  private PriceRateSimple priceRate;
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

  public SimpleRate getFoodRate() {
    return foodRate;
  }

  public void setFoodRate(SimpleRate foodRate) {
    this.foodRate = foodRate;
  }

  public PriceRateSimple getPriceRate() {
    return priceRate;
  }

  public void setPriceRate(PriceRateSimple priceRate) {
    this.priceRate = priceRate;
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
    return "FoodPlaceDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", created=" + created +
        ", modified=" + modified +
        ", foodRate=" + foodRate +
        ", priceRate=" + priceRate +
        ", typeId=" + typeId +
        ", addressId=" + addressId +
        '}';
  }
}
