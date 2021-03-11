package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.AreaPlace;
import tech.przybysz.pms.locationsservice.service.dto.AreaPlaceDTO;

/**
 * Mapper for the entity {@link AreaPlace} and its DTO {@link AreaPlaceDTO}.
 */
@Mapper(componentModel = "spring", uses = {AreaPlaceTypeMapper.class, CityTownSpotMapper.class})
public interface AreaPlaceMapper extends EntityMapper<AreaPlaceDTO, AreaPlace> {

  @Mapping(source = "type.id", target = "typeId")
  @Mapping(source = "spot.id", target = "spotId")
  AreaPlaceDTO toDto(AreaPlace areaPlace);

  @Mapping(source = "typeId", target = "type")
  @Mapping(source = "spotId", target = "spot")
  @Mapping(target = "images", ignore = true)
  @Mapping(target = "points", ignore = true)
  @Mapping(target = "comments", ignore = true)
  AreaPlace toEntity(AreaPlaceDTO areaPlaceDTO);

  default AreaPlace fromId(Long id) {
    if (id == null) {
      return null;
    }
    AreaPlace areaPlace = new AreaPlace();
    areaPlace.setId(id);
    return areaPlace;
  }
}
