package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.PointLocationType;
import tech.przybysz.pms.locationsservice.service.dto.PointLocationTypeDTO;

/**
 * Mapper for the entity {@link PointLocationType} and its DTO {@link PointLocationTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PointLocationTypeMapper extends EntityMapper<PointLocationTypeDTO, PointLocationType> {

  PointLocationTypeDTO toDto(PointLocationType pointLocationType);

  @Mapping(target = "places", ignore = true)
  PointLocationType toEntity(PointLocationTypeDTO pointLocationTypeDTO);

  default PointLocationType fromId(Long id) {
    if (id == null) {
      return null;
    }
    PointLocationType pointLocationType = new PointLocationType();
    pointLocationType.setId(id);
    return pointLocationType;
  }
}
