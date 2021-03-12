package tech.przybysz.pms.locationsservice.service.dto;

public class AddressDTO {
  private Long id;
  private String lineOne;
  private String lineTwo;
  private Long cityTownId;

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

  public Long getCityTownId() {
    return cityTownId;
  }

  public void setCityTownId(Long cityTownId) {
    this.cityTownId = cityTownId;
  }

  @Override
  public String toString() {
    return "AddressDTO{" +
        "id=" + id +
        ", lineOne='" + lineOne + '\'' +
        ", lineTwo='" + lineTwo + '\'' +
        ", cityTownId=" + cityTownId +
        '}';
  }
}
