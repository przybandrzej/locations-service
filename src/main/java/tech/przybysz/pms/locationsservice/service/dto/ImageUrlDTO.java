package tech.przybysz.pms.locationsservice.service.dto;

public class ImageUrlDTO {
  private Long id;
  private String url;
  private Float order;
  private Long areaPlaceId;
  private Long foodPlaceId;
  private Long stayPlaceId;
  private Long pointLocationId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Float getOrder() {
    return order;
  }

  public void setOrder(Float order) {
    this.order = order;
  }

  public Long getAreaPlaceId() {
    return areaPlaceId;
  }

  public void setAreaPlaceId(Long areaPlaceId) {
    this.areaPlaceId = areaPlaceId;
  }

  public Long getFoodPlaceId() {
    return foodPlaceId;
  }

  public void setFoodPlaceId(Long foodPlaceId) {
    this.foodPlaceId = foodPlaceId;
  }

  public Long getStayPlaceId() {
    return stayPlaceId;
  }

  public void setStayPlaceId(Long stayPlaceId) {
    this.stayPlaceId = stayPlaceId;
  }

  public Long getPointLocationId() {
    return pointLocationId;
  }

  public void setPointLocationId(Long pointLocationId) {
    this.pointLocationId = pointLocationId;
  }

  @Override
  public String toString() {
    return "ImageUrlDTO{" +
        "id=" + id +
        ", url='" + url + '\'' +
        ", order=" + order +
        ", areaPlaceId=" + areaPlaceId +
        ", foodPlaceId=" + foodPlaceId +
        ", stayPlaceId=" + stayPlaceId +
        ", pointLocationId=" + pointLocationId +
        '}';
  }
}
