package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.AreaPlaceType;
import tech.przybysz.pms.locationsservice.service.dto.AreaPlaceTypeDTO;

/**
 * Mapper for the entity {@link AreaPlaceType} and its DTO {@link AreaPlaceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AreaPlaceTypeMapper extends EntityMapper<AreaPlaceTypeDTO, AreaPlaceType> {

  AreaPlaceTypeDTO toDto(AreaPlaceType areaPlaceType);

  @Mapping(target = "places", ignore = true)
  AreaPlaceType toEntity(AreaPlaceTypeDTO areaPlaceTypeDTO);

  default AreaPlaceType fromId(Long id) {
    if (id == null) {
      return null;
    }
    AreaPlaceType areaPlaceType = new AreaPlaceType();
    areaPlaceType.setId(id);
    return areaPlaceType;
  }
}
