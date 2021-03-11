package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.StayPlaceType;
import tech.przybysz.pms.locationsservice.service.dto.StayPlaceTypeDTO;

/**
 * Mapper for the entity {@link StayPlaceType} and its DTO {@link StayPlaceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StayPlaceTypeMapper extends EntityMapper<StayPlaceTypeDTO, StayPlaceType> {

  StayPlaceTypeDTO toDto(StayPlaceType stayPlaceType);

  @Mapping(target = "places", ignore = true)
  StayPlaceType toEntity(StayPlaceTypeDTO stayPlaceTypeDTO);

  default StayPlaceType fromId(Long id) {
    if (id == null) {
      return null;
    }
    StayPlaceType stayPlaceType = new StayPlaceType();
    stayPlaceType.setId(id);
    return stayPlaceType;
  }
}
