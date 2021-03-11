package tech.przybysz.pms.locationsservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "content")
  private String content;

  @Column(name = "created")
  private LocalDateTime created;

  @ManyToOne
  @JoinColumn(name = "area_place_id", referencedColumnName = "id")
  private AreaPlace areaPlace;

  @ManyToOne
  @JoinColumn(name = "food_place_id", referencedColumnName = "id")
  private FoodPlace foodPlace;

  @ManyToOne
  @JoinColumn(name = "stay_place_id", referencedColumnName = "id")
  private StayPlace stayPlace;

  @ManyToOne
  @JoinColumn(name = "point_location_id", referencedColumnName = "id")
  private PointLocation pointLocation;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public AreaPlace getAreaPlace() {
    return areaPlace;
  }

  public void setAreaPlace(AreaPlace areaPlace) {
    this.areaPlace = areaPlace;
  }

  public FoodPlace getFoodPlace() {
    return foodPlace;
  }

  public void setFoodPlace(FoodPlace foodPlace) {
    this.foodPlace = foodPlace;
  }

  public StayPlace getStayPlace() {
    return stayPlace;
  }

  public void setStayPlace(StayPlace stayPlace) {
    this.stayPlace = stayPlace;
  }

  public PointLocation getPointLocation() {
    return pointLocation;
  }

  public void setPointLocation(PointLocation pointLocation) {
    this.pointLocation = pointLocation;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(!(o instanceof Comment)) {
      return false;
    }
    return id != null && id.equals(((Comment) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
