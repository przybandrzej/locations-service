package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.Address;
import tech.przybysz.pms.locationsservice.service.dto.AddressDTO;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {AreaPlaceTypeMapper.class, FoodPlaceMapper.class, StayPlaceMapper.class, PointLocationMapper.class})
public interface ImageUrlMapper extends EntityMapper<AddressDTO, Address> {

  @Mapping(source = "areaPlace.id", target = "areaPlaceId")
  @Mapping(source = "foodPlace.id", target = "foodPlaceId")
  @Mapping(source = "stayPlace.id", target = "stayPlaceId")
  @Mapping(source = "pointLocation.id", target = "pointLocationId")
  AddressDTO toDto(Address address);

  @Mapping(source = "pointLocationId", target = "pointLocation")
  @Mapping(source = "foodPlaceId", target = "foodPlace")
  @Mapping(source = "stayPlaceId", target = "stayPlace")
  @Mapping(source = "areaPlaceId", target = "areaPlace")
  Address toEntity(AddressDTO addressDTO);

  default Address fromId(Long id) {
    if (id == null) {
      return null;
    }
    Address address = new Address();
    address.setId(id);
    return address;
  }
}
