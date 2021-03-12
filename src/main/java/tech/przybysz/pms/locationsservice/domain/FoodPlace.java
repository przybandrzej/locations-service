package tech.przybysz.pms.locationsservice.domain;

import tech.przybysz.pms.locationsservice.domain.enumeration.PriceRateSimple;
import tech.przybysz.pms.locationsservice.domain.enumeration.SimpleRate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "food_place")
public class FoodPlace implements Serializable {

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
  @Column(name = "food_rate")
  private SimpleRate foodRate;

  @Enumerated(EnumType.STRING)
  @Column(name = "price_rate")
  private PriceRateSimple priceRate;

  @ManyToOne
  @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
  private FoodPlaceType type;

  @ManyToOne
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @OneToMany(mappedBy = "foodPlace")
  private Set<ImageUrl> images = new HashSet<>();

  @OneToMany(mappedBy = "foodPlace")
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

  public FoodPlaceType getType() {
    return type;
  }

  public void setType(FoodPlaceType type) {
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

  public SimpleRate getFoodRate() {
    return foodRate;
  }

  public void setFoodRate(SimpleRate foodRate) {
    this.foodRate = foodRate;
  }

  public PriceRateSimple getPriceRate() {
    return priceRate;
  }

  public void setPriceRate(PriceRateSimple priceRate) {
    this.priceRate = priceRate;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(!(o instanceof FoodPlace)) {
      return false;
    }
    return id != null && id.equals(((FoodPlace) o).id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
