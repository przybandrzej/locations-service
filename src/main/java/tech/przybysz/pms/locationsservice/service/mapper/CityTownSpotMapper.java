package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.CityTownSpot;
import tech.przybysz.pms.locationsservice.service.dto.CityTownSpotDTO;

/**
 * Mapper for the entity {@link CityTownSpot} and its DTO {@link CityTownSpotDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CityTownSpotMapper extends EntityMapper<CityTownSpotDTO, CityTownSpot> {

  @Mapping(source = "parent.id", target = "parentId")
  CityTownSpotDTO toDto(CityTownSpot cityTownSpot);

  @Mapping(source = "parentId", target = "parent")
  CityTownSpot toEntity(CityTownSpotDTO cityTownSpotDTO);

  default CityTownSpot fromId(Long id) {
    if (id == null) {
      return null;
    }
    CityTownSpot cityTownSpot = new CityTownSpot();
    cityTownSpot.setId(id);
    return cityTownSpot;
  }
}
