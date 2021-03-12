package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.PointLocation;
import tech.przybysz.pms.locationsservice.repository.*;
import tech.przybysz.pms.locationsservice.service.PointLocationService;
import tech.przybysz.pms.locationsservice.service.dto.PointLocationDTO;
import tech.przybysz.pms.locationsservice.service.exception.DependencyEntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.PointLocationMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PointLocationServiceImpl implements PointLocationService {

  private final Logger log = LoggerFactory.getLogger(PointLocationServiceImpl.class);
  public static final String ENTITY_NAME = "point-location";

  private final PointLocationMapper mapper;
  private final PointLocationRepository repository;
  private final AreaPlaceRepository areaPlaceRepository;
  private final PointLocationTypeRepository pointLocationTypeRepository;
  private final CommentRepository commentRepository;
  private final ImageUrlRepository imageUrlRepository;


  public PointLocationServiceImpl(PointLocationMapper mapper, PointLocationRepository repository,
                                  AreaPlaceRepository areaPlaceRepository,
                                  PointLocationTypeRepository pointLocationTypeRepository,
                                  CommentRepository commentRepository, ImageUrlRepository imageUrlRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.areaPlaceRepository = areaPlaceRepository;
    this.pointLocationTypeRepository = pointLocationTypeRepository;
    this.commentRepository = commentRepository;
    this.imageUrlRepository = imageUrlRepository;
  }

  @Override
  public PointLocationDTO create(PointLocationDTO pointLocationDTO) {
    log.debug("Request to get create PointLocation {}", pointLocationDTO);
    if(pointLocationDTO.getAreaPlaceId() != null && !areaPlaceRepository.existsById(pointLocationDTO.getAreaPlaceId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "area-place");
    }
    if(pointLocationDTO.getTypeId() == null || !pointLocationTypeRepository.existsById(pointLocationDTO.getTypeId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "point-location-type");
    }

    PointLocation pointLocation = mapper.toEntity(pointLocationDTO);
    pointLocation.setId(null);
    pointLocation.setCreated(LocalDateTime.now());
    pointLocation.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(pointLocation));
  }

  @Override
  public PointLocationDTO update(PointLocationDTO pointLocationDTO) {
    log.debug("Request to get update PointLocation {}", pointLocationDTO);
    if(pointLocationDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<PointLocation> tmp = repository.findById(pointLocationDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    if(pointLocationDTO.getAreaPlaceId() != null && !areaPlaceRepository.existsById(pointLocationDTO.getAreaPlaceId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "area-place");
    }
    if(pointLocationDTO.getTypeId() == null || !pointLocationTypeRepository.existsById(pointLocationDTO.getTypeId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "point-location-type");
    }

    PointLocation pointLocation = mapper.toEntity(pointLocationDTO);
    pointLocation.setCreated(tmp.get().getCreated());
    pointLocation.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(pointLocation));
  }

  @Override
  public List<PointLocationDTO> findAll() {
    log.debug("Request to get all PointLocations");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<PointLocationDTO> findOne(Long id) {
    log.debug("Request to get PointLocation {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete PointLocation {}", id);
    Optional<PointLocation> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    commentRepository.deleteAll(tmp.get().getComments());
    imageUrlRepository.deleteAll(tmp.get().getImages());

    repository.deleteById(id);
  }
}
