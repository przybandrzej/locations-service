package tech.przybysz.pms.locationsservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stay_place_type")
public class StayPlaceType implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "created")
  private LocalDateTime created;

  @Column(name = "modified")
  private LocalDateTime modified;

  @OneToMany(mappedBy = "type")
  private Set<StayPlace> places = new HashSet<>();

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

  public Set<StayPlace> getPlaces() {
    return places;
  }

  public void setPlaces(Set<StayPlace> places) {
    this.places = places;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(!(o instanceof StayPlaceType)) {
      return false;
    }
    return id != null && id.equals(((StayPlaceType) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
