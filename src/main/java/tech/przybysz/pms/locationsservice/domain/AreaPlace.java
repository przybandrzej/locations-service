package tech.przybysz.pms.locationsservice.domain;

import tech.przybysz.pms.locationsservice.domain.enumeration.AreaUnit;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "area_place")
public class AreaPlace implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "created")
  private LocalDateTime created;

  @Column(name = "modified")
  private LocalDateTime modified;

  @Column(name = "area")
  private Double area;

  @Enumerated(EnumType.STRING)
  @Column(name = "unit")
  private AreaUnit unit;

  @Column(name = "paid_admission")
  private Boolean paidAdmission;

  @Column(name = "pets_allowed")
  private Boolean petsAllowed;

  @ManyToOne
  @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
  private AreaPlaceType type;

  @ManyToOne
  @JoinColumn(name = "spot_id", referencedColumnName = "id")
  private CityTownSpot spot;

  @OneToMany(mappedBy = "areaPlace")
  private Set<ImageUrl> images = new HashSet<>();

  @OneToMany(mappedBy = "areaPlace")
  private Set<Comment> comments = new HashSet<>();

  @OneToMany(mappedBy = "areaPlace")
  private Set<PointLocation> points = new HashSet<>();

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

  public AreaPlaceType getType() {
    return type;
  }

  public void setType(AreaPlaceType type) {
    this.type = type;
  }

  public Set<ImageUrl> getImages() {
    return images;
  }

  public void setImages(Set<ImageUrl> images) {
    this.images = images;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getPaidAdmission() {
    return paidAdmission;
  }

  public void setPaidAdmission(Boolean paidAdmission) {
    this.paidAdmission = paidAdmission;
  }

  public Set<PointLocation> getPoints() {
    return points;
  }

  public void setPoints(Set<PointLocation> points) {
    this.points = points;
  }

  public CityTownSpot getSpot() {
    return spot;
  }

  public void setSpot(CityTownSpot spot) {
    this.spot = spot;
  }

  public Boolean getPetsAllowed() {
    return petsAllowed;
  }

  public void setPetsAllowed(Boolean petsAllowed) {
    this.petsAllowed = petsAllowed;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(!(o instanceof AreaPlace)) {
      return false;
    }
    return id != null && id.equals(((AreaPlace) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
