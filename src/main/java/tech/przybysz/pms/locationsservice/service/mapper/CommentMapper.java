package tech.przybysz.pms.locationsservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.przybysz.pms.locationsservice.domain.Comment;
import tech.przybysz.pms.locationsservice.service.dto.CommentDTO;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {AreaPlaceTypeMapper.class, FoodPlaceMapper.class, StayPlaceMapper.class, PointLocationMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

  @Mapping(source = "areaPlace.id", target = "areaPlaceId")
  @Mapping(source = "foodPlace.id", target = "foodPlaceId")
  @Mapping(source = "stayPlace.id", target = "stayPlaceId")
  @Mapping(source = "pointLocation.id", target = "pointLocationId")
  CommentDTO toDto(Comment comment);

  @Mapping(source = "areaPlaceId", target = "areaPlace")
  @Mapping(source = "foodPlaceId", target = "foodPlace")
  @Mapping(source = "stayPlaceId", target = "stayPlace")
  @Mapping(source = "pointLocationId", target = "pointLocation")
  Comment toEntity(CommentDTO commentDTO);

  default Comment fromId(Long id) {
    if(id == null) {
      return null;
    }
    Comment comment = new Comment();
    comment.setId(id);
    return comment;
  }
}
