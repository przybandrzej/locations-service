package tech.przybysz.pms.locationsservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "city_town_spot")
public class CityTownSpot implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "latitude")
  private BigDecimal latitude;

  @Column(name = "longitude")
  private BigDecimal longitude;

  @ManyToOne
  @JoinColumn(name = "parent_id", referencedColumnName = "id")
  private CityTownSpot parent;

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

  public CityTownSpot getParent() {
    return parent;
  }

  public void setParent(CityTownSpot parent) {
    this.parent = parent;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(!(o instanceof CityTownSpot)) {
      return false;
    }
    return id != null && id.equals(((CityTownSpot) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
