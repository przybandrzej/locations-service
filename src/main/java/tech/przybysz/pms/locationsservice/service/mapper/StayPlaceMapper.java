package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.StayPlace;
import tech.przybysz.pms.locationsservice.service.dto.StayPlaceDTO;

/**
 * Mapper for the entity {@link StayPlace} and its DTO {@link StayPlaceDTO}.
 */
@Mapper(componentModel = "spring", uses = {StayPlaceTypeMapper.class, AddressMapper.class})
public interface StayPlaceMapper extends EntityMapper<StayPlaceDTO, StayPlace> {

  @Mapping(source = "type.id", target = "typeId")
  @Mapping(source = "address.id", target = "addressId")
  StayPlaceDTO toDto(StayPlace stayPlace);

  @Mapping(source = "typeId", target = "type")
  @Mapping(source = "addressId", target = "address")
  @Mapping(target = "images", ignore = true)
  @Mapping(target = "comments", ignore = true)
  StayPlace toEntity(StayPlaceDTO stayPlaceDTO);

  default StayPlace fromId(Long id) {
    if (id == null) {
      return null;
    }
    StayPlace stayPlace = new StayPlace();
    stayPlace.setId(id);
    return stayPlace;
  }
}
