package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.FoodPlace;
import tech.przybysz.pms.locationsservice.service.dto.FoodPlaceDTO;

/**
 * Mapper for the entity {@link FoodPlace} and its DTO {@link FoodPlaceDTO}.
 */
@Mapper(componentModel = "spring", uses = {FoodPlaceTypeMapper.class, AddressMapper.class})
public interface FoodPlaceMapper extends EntityMapper<FoodPlaceDTO, FoodPlace> {

  @Mapping(source = "type.id", target = "typeId")
  @Mapping(source = "address.id", target = "addressId")
  FoodPlaceDTO toDto(FoodPlace foodPlace);

  @Mapping(source = "typeId", target = "type")
  @Mapping(source = "addressId", target = "address")
  @Mapping(target = "images", ignore = true)
  @Mapping(target = "comments", ignore = true)
  FoodPlace toEntity(FoodPlaceDTO foodPlaceDTO);

  default FoodPlace fromId(Long id) {
    if (id == null) {
      return null;
    }
    FoodPlace foodPlace = new FoodPlace();
    foodPlace.setId(id);
    return foodPlace;
  }
}
