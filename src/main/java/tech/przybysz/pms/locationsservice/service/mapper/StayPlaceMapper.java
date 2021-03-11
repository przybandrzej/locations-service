package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.AreaPlace;
import tech.przybysz.pms.locationsservice.service.dto.AreaPlaceDTO;

/**
 * Mapper for the entity {@link AreaPlace} and its DTO {@link AreaPlaceDTO}.
 */
@Mapper(componentModel = "spring", uses = {StayPlaceTypeMapper.class, AddressMapper.class})
public interface StayPlaceMapper extends EntityMapper<AreaPlaceDTO, AreaPlace> {

  @Mapping(source = "type.id", target = "typeId")
  @Mapping(source = "address.id", target = "addressId")
  AreaPlaceDTO toDto(AreaPlace areaPlace);

  @Mapping(source = "typeId", target = "type")
  @Mapping(source = "addressId", target = "address")
  @Mapping(target = "images", ignore = true)
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
