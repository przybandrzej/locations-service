package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.ImageUrl;
import tech.przybysz.pms.locationsservice.service.dto.ImageUrlDTO;

/**
 * Mapper for the entity {@link ImageUrl} and its DTO {@link ImageUrlDTO}.
 */
@Mapper(componentModel = "spring", uses = {AreaPlaceMapper.class, FoodPlaceMapper.class, StayPlaceMapper.class, PointLocationMapper.class})
public interface ImageUrlMapper extends EntityMapper<ImageUrlDTO, ImageUrl> {

  @Mapping(source = "areaPlace.id", target = "areaPlaceId")
  @Mapping(source = "foodPlace.id", target = "foodPlaceId")
  @Mapping(source = "stayPlace.id", target = "stayPlaceId")
  @Mapping(source = "pointLocation.id", target = "pointLocationId")
  ImageUrlDTO toDto(ImageUrl imageUrl);

  @Mapping(source = "pointLocationId", target = "pointLocation")
  @Mapping(source = "foodPlaceId", target = "foodPlace")
  @Mapping(source = "stayPlaceId", target = "stayPlace")
  @Mapping(source = "areaPlaceId", target = "areaPlace")
  ImageUrl toEntity(ImageUrlDTO imageUrlDTO);

  default ImageUrl fromId(Long id) {
    if(id == null) {
      return null;
    }
    ImageUrl imageUrl = new ImageUrl();
    imageUrl.setId(id);
    return imageUrl;
  }
}
