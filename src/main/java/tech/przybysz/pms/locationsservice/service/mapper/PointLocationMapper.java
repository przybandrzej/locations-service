package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.PointLocation;
import tech.przybysz.pms.locationsservice.service.dto.PointLocationDTO;

/**
 * Mapper for the entity {@link PointLocation} and its DTO {@link PointLocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {PointLocationTypeMapper.class, AreaPlaceMapper.class})
public interface PointLocationMapper extends EntityMapper<PointLocationDTO, PointLocation> {

  @Mapping(source = "type.id", target = "typeId")
  @Mapping(source = "areaPlace.id", target = "areaPlaceId")
  PointLocationDTO toDto(PointLocation pointLocation);

  @Mapping(source = "typeId", target = "type")
  @Mapping(source = "areaPlaceId", target = "areaPlace")
  @Mapping(target = "images", ignore = true)
  @Mapping(target = "comments", ignore = true)
  PointLocation toEntity(PointLocationDTO pointLocationDTO);

  default PointLocation fromId(Long id) {
    if (id == null) {
      return null;
    }
    PointLocation pointLocation = new PointLocation();
    pointLocation.setId(id);
    return pointLocation;
  }
}
