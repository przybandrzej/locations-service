package tech.przybysz.pms.locationsservice.service.dto;

import java.time.LocalDateTime;

public class CommentDTO {
  private Long id;
  private String content;
  private LocalDateTime created;
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
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
    return "CommentDTO{" +
        "id=" + id +
        ", content='" + content + '\'' +
        ", created=" + created +
        ", areaPlaceId=" + areaPlaceId +
        ", foodPlaceId=" + foodPlaceId +
        ", stayPlaceId=" + stayPlaceId +
        ", pointLocationId=" + pointLocationId +
        '}';
  }
}
