package tech.przybysz.pms.locationsservice.domain;

import tech.przybysz.pms.locationsservice.domain.enumeration.PriceRateSimple;
import tech.przybysz.pms.locationsservice.domain.enumeration.SimpleRate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stay_place")
public class StayPlace implements Serializable {

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

  @Enumerated(EnumType.STRING)
  @Column(name = "peace_rate")
  private SimpleRate peaceRate;

  @Enumerated(EnumType.STRING)
  @Column(name = "location_rate")
  private SimpleRate locationRate;

  @Enumerated(EnumType.STRING)
  @Column(name = "price_rate")
  private PriceRateSimple priceRate;

  @Column(name = "pets_allowed")
  private Boolean petsAllowed;

  @Column(name = "has_parking")
  private Boolean hasParking;

  @Enumerated(EnumType.STRING)
  @Column(name = "comfort_rate")
  private SimpleRate comfortRate;

  @Enumerated(EnumType.STRING)
  @Column(name = "standard_rate")
  private SimpleRate standardRate;

  @ManyToOne
  @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
  private StayPlaceType type;

  @ManyToOne
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address addresses;

  @OneToMany(mappedBy = "stayPlace")
  private Set<ImageUrl> images = new HashSet<>();

  @OneToMany(mappedBy = "stayPlace")
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

  public StayPlaceType getType() {
    return type;
  }

  public void setType(StayPlaceType type) {
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

  public Address getAddresses() {
    return addresses;
  }

  public void setAddresses(Address addresses) {
    this.addresses = addresses;
  }

  public SimpleRate getPeaceRate() {
    return peaceRate;
  }

  public void setPeaceRate(SimpleRate peaceRate) {
    this.peaceRate = peaceRate;
  }

  public SimpleRate getLocationRate() {
    return locationRate;
  }

  public void setLocationRate(SimpleRate locationRate) {
    this.locationRate = locationRate;
  }

  public PriceRateSimple getPriceRate() {
    return priceRate;
  }

  public void setPriceRate(PriceRateSimple priceRate) {
    this.priceRate = priceRate;
  }

  public Boolean getPetsAllowed() {
    return petsAllowed;
  }

  public void setPetsAllowed(Boolean petsAllowed) {
    this.petsAllowed = petsAllowed;
  }

  public Boolean getHasParking() {
    return hasParking;
  }

  public void setHasParking(Boolean hasParking) {
    this.hasParking = hasParking;
  }

  public SimpleRate getComfortRate() {
    return comfortRate;
  }

  public void setComfortRate(SimpleRate comfortRate) {
    this.comfortRate = comfortRate;
  }

  public SimpleRate getStandardRate() {
    return standardRate;
  }

  public void setStandardRate(SimpleRate standardRate) {
    this.standardRate = standardRate;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(!(o instanceof StayPlace)) {
      return false;
    }
    return id != null && id.equals(((StayPlace) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
