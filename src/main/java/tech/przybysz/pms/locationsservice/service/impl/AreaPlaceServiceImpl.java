package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.AreaPlace;
import tech.przybysz.pms.locationsservice.domain.Comment;
import tech.przybysz.pms.locationsservice.domain.ImageUrl;
import tech.przybysz.pms.locationsservice.domain.PointLocation;
import tech.przybysz.pms.locationsservice.repository.*;
import tech.przybysz.pms.locationsservice.service.AreaPlaceService;
import tech.przybysz.pms.locationsservice.service.dto.AreaPlaceDTO;
import tech.przybysz.pms.locationsservice.service.exception.DependencyEntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.AreaPlaceMapper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AreaPlaceServiceImpl implements AreaPlaceService {

  private final Logger log = LoggerFactory.getLogger(AreaPlaceServiceImpl.class);
  public static final String ENTITY_NAME = "area-place";

  private final AreaPlaceMapper mapper;
  private final AreaPlaceRepository repository;
  private final PointLocationRepository pointLocationRepository;
  private final CommentRepository commentRepository;
  private final ImageUrlRepository imageUrlRepository;
  private final CityTownSpotRepository cityTownSpotRepository;
  private final AreaPlaceTypeRepository areaPlaceTypeRepository;

  public AreaPlaceServiceImpl(AreaPlaceMapper mapper, AreaPlaceRepository repository,
                              PointLocationRepository pointLocationRepository, CommentRepository commentRepository,
                              ImageUrlRepository imageUrlRepository, CityTownSpotRepository cityTownSpotRepository,
                              AreaPlaceTypeRepository areaPlaceTypeRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.pointLocationRepository = pointLocationRepository;
    this.commentRepository = commentRepository;
    this.imageUrlRepository = imageUrlRepository;
    this.cityTownSpotRepository = cityTownSpotRepository;
    this.areaPlaceTypeRepository = areaPlaceTypeRepository;
  }

  @Override
  public AreaPlaceDTO create(AreaPlaceDTO areaPlaceDTO) {
    log.debug("Request to get create AreaPlace {}", areaPlaceDTO);
    if(areaPlaceDTO.getSpotId() == null || !cityTownSpotRepository.existsById(areaPlaceDTO.getSpotId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "city-town-spot");
    }
    if(areaPlaceDTO.getTypeId() == null || !areaPlaceTypeRepository.existsById(areaPlaceDTO.getTypeId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "area-place-type");
    }

    AreaPlace areaPlace = mapper.toEntity(areaPlaceDTO);
    areaPlace.setId(null);
    areaPlace.setCreated(LocalDateTime.now());
    areaPlace.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(areaPlace));
  }

  @Override
  public AreaPlaceDTO update(AreaPlaceDTO areaPlaceDTO) {
    log.debug("Request to get update AreaPlace {}", areaPlaceDTO);
    if(areaPlaceDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<AreaPlace> tmp = repository.findById(areaPlaceDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    if(areaPlaceDTO.getSpotId() == null || !cityTownSpotRepository.existsById(areaPlaceDTO.getSpotId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "city-town-spot");
    }
    if(areaPlaceDTO.getTypeId() == null || !areaPlaceTypeRepository.existsById(areaPlaceDTO.getTypeId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "area-place-type");
    }

    AreaPlace areaPlace = mapper.toEntity(areaPlaceDTO);
    areaPlace.setCreated(tmp.get().getCreated());
    areaPlace.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(areaPlace));
  }

  @Override
  public List<AreaPlaceDTO> findAll() {
    log.debug("Request to get all AreaPlaces");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<AreaPlaceDTO> findOne(Long id) {
    log.debug("Request to get AreaPlace {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete AreaPlace {}", id);
    Optional<AreaPlace> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    Collection<Comment> comments = commentRepository.findAllByAreaPlaceId(id);
    commentRepository.deleteAll(comments);
    Collection<ImageUrl> images = imageUrlRepository.findAllByAreaPlaceId(id);
    imageUrlRepository.deleteAll(images);
    Collection<PointLocation> points = pointLocationRepository.findAllByAreaPlaceId(id);
    points.forEach(it -> it.setAreaPlace(null));
    pointLocationRepository.saveAll(points);

    repository.deleteById(id);
  }
}
