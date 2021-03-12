package tech.przybysz.pms.locationsservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "point_location")
public class PointLocation implements Serializable {

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

  @Column(name = "latitude")
  private BigDecimal latitude;

  @Column(name = "longitude")
  private BigDecimal longitude;

  @ManyToOne
  @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
  private PointLocationType type;

  @ManyToOne
  @JoinColumn(name = "area_place_id", referencedColumnName = "id")
  private AreaPlace areaPlace;

  @OneToMany(mappedBy = "pointLocation")
  private Set<ImageUrl> images = new HashSet<>();

  @OneToMany(mappedBy = "pointLocation")
  private Set<Comment> comments = new HashSet<>();

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

  public PointLocationType getType() {
    return type;
  }

  public void setType(PointLocationType type) {
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

  public AreaPlace getAreaPlace() {
    return areaPlace;
  }

  public void setAreaPlace(AreaPlace areaPlace) {
    this.areaPlace = areaPlace;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(!(o instanceof PointLocation)) {
      return false;
    }
    return id != null && id.equals(((PointLocation) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
