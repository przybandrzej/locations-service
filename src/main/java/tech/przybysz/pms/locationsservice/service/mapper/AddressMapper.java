package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.Address;
import tech.przybysz.pms.locationsservice.service.dto.AddressDTO;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {CityTownSpotMapper.class, FoodPlaceMapper.class, StayPlaceMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

  @Mapping(source = "cityTown.id", target = "cityTownId")
  @Mapping(source = "foodPlace.id", target = "foodPlaceId")
  @Mapping(source = "stayPlace.id", target = "stayPlaceId")
  AddressDTO toDto(Address address);

  @Mapping(source = "cityTownId", target = "cityTown")
  @Mapping(source = "foodPlaceId", target = "foodPlace")
  @Mapping(source = "stayPlaceId", target = "stayPlace")
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
