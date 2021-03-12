package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.Comment;
import tech.przybysz.pms.locationsservice.repository.*;
import tech.przybysz.pms.locationsservice.service.CommentService;
import tech.przybysz.pms.locationsservice.service.dto.CommentDTO;
import tech.przybysz.pms.locationsservice.service.exception.DependencyEntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.CommentMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

  private final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
  public static final String ENTITY_NAME = "comment";

  private final CommentMapper mapper;
  private final CommentRepository repository;
  private final FoodPlaceRepository foodPlaceRepository;
  private final StayPlaceRepository stayPlaceRepository;
  private final AreaPlaceRepository areaPlaceRepository;
  private final PointLocationRepository pointLocationRepository;

  public CommentServiceImpl(CommentMapper mapper, CommentRepository repository, FoodPlaceRepository foodPlaceRepository,
                            StayPlaceRepository stayPlaceRepository, AreaPlaceRepository areaPlaceRepository,
                            PointLocationRepository pointLocationRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.foodPlaceRepository = foodPlaceRepository;
    this.stayPlaceRepository = stayPlaceRepository;
    this.areaPlaceRepository = areaPlaceRepository;
    this.pointLocationRepository = pointLocationRepository;
  }

  @Override
  public CommentDTO create(CommentDTO commentDTO) {
    log.debug("Request to get create Comment {}", commentDTO);
    nullCheck(commentDTO);
    Comment comment = mapper.toEntity(commentDTO);
    comment.setId(null);
    comment.setCreated(LocalDateTime.now());
    return mapper.toDto(repository.save(comment));
  }

  @Override
  public CommentDTO update(CommentDTO commentDTO) {
    log.debug("Request to get update Comment {}", commentDTO);
    if(commentDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<Comment> tmp = repository.findById(commentDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    nullCheck(commentDTO);
    Comment comment = mapper.toEntity(commentDTO);
    comment.setCreated(tmp.get().getCreated());
    return mapper.toDto(repository.save(comment));
  }

  private void nullCheck(CommentDTO commentDTO) {
    if(commentDTO.getAreaPlaceId() != null && !areaPlaceRepository.existsById(commentDTO.getAreaPlaceId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "area-place");
    }
    if(commentDTO.getFoodPlaceId() != null && !foodPlaceRepository.existsById(commentDTO.getFoodPlaceId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "food-place");
    }
    if(commentDTO.getStayPlaceId() != null && !stayPlaceRepository.existsById(commentDTO.getStayPlaceId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "stay-place");
    }
    if(commentDTO.getPointLocationId() != null && !pointLocationRepository.existsById(commentDTO.getPointLocationId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "point-location");
    }
    if(commentDTO.getFoodPlaceId() == null && commentDTO.getAreaPlaceId() == null && commentDTO.getStayPlaceId() == null
        && commentDTO.getPointLocationId() == null) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "food-place/area-place/stay-place/point-location");
    }
  }

  @Override
  public List<CommentDTO> findAll() {
    log.debug("Request to get all Comments");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<CommentDTO> findOne(Long id) {
    log.debug("Request to get Comment {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Comment {}", id);
    Optional<Comment> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    repository.deleteById(id);
  }

  @Override
  public List<CommentDTO> findAllOfAreaPlace(Long areaPlaceId) {
    return repository.findAllByAreaPlaceId(areaPlaceId).stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<CommentDTO> findAllOfFoodPlace(Long foodPlaceId) {
    return repository.findAllByFoodPlaceId(foodPlaceId).stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<CommentDTO> findAllOfStayPlace(Long stayPlaceId) {
    return repository.findAllByStayPlaceId(stayPlaceId).stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<CommentDTO> findAllOfPointLocation(Long pointLocationId) {
    return repository.findAllByPointLocationId(pointLocationId).stream().map(mapper::toDto).collect(Collectors.toList());
  }
}
