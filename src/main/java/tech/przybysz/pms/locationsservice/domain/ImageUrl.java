package tech.przybysz.pms.locationsservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "image_url")
public class ImageUrl implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "url")
  private String url;

  @Column(name = "order")
  private Float order;

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
    if(!(o instanceof ImageUrl)) {
      return false;
    }
    return id != null && id.equals(((ImageUrl) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
