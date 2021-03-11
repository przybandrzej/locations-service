package tech.przybysz.pms.locationsservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "line_one")
  private String lineOne;

  @Column(name = "line_two")
  private String lineTwo;

  @ManyToOne
  @JoinColumn(name = "city_town_spot_id", referencedColumnName = "id")
  private CityTownSpot cityTown;

  @ManyToOne
  @JoinColumn(name = "food_place_id", referencedColumnName = "id")
  private FoodPlace foodPlace;

  @ManyToOne
  @JoinColumn(name = "stay_place_id", referencedColumnName = "id")
  private StayPlace stayPlace;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLineOne() {
    return lineOne;
  }

  public void setLineOne(String lineOne) {
    this.lineOne = lineOne;
  }

  public String getLineTwo() {
    return lineTwo;
  }

  public void setLineTwo(String lineTwo) {
    this.lineTwo = lineTwo;
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

  public CityTownSpot getCityTown() {
    return cityTown;
  }

  public void setCityTown(CityTownSpot cityTown) {
    this.cityTown = cityTown;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(!(o instanceof Address)) {
      return false;
    }
    return id != null && id.equals(((Address) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
