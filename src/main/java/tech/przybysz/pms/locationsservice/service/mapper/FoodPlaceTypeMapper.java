package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.FoodPlaceType;
import tech.przybysz.pms.locationsservice.service.dto.FoodPlaceTypeDTO;

/**
 * Mapper for the entity {@link FoodPlaceType} and its DTO {@link FoodPlaceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FoodPlaceTypeMapper extends EntityMapper<FoodPlaceTypeDTO, FoodPlaceType> {

  FoodPlaceTypeDTO toDto(FoodPlaceType foodPlaceType);

  @Mapping(target = "places", ignore = true)
  FoodPlaceType toEntity(FoodPlaceTypeDTO foodPlaceTypeDTO);

  default FoodPlaceType fromId(Long id) {
    if (id == null) {
      return null;
    }
    FoodPlaceType foodPlaceType = new FoodPlaceType();
    foodPlaceType.setId(id);
    return foodPlaceType;
  }
}
