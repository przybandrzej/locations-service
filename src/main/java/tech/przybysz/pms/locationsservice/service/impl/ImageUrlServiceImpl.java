package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.ImageUrl;
import tech.przybysz.pms.locationsservice.repository.*;
import tech.przybysz.pms.locationsservice.service.ImageUrlService;
import tech.przybysz.pms.locationsservice.service.dto.ImageUrlDTO;
import tech.przybysz.pms.locationsservice.service.exception.DependencyEntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.ImageUrlMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ImageUrlServiceImpl implements ImageUrlService {

  private final Logger log = LoggerFactory.getLogger(ImageUrlServiceImpl.class);
  public static final String ENTITY_NAME = "image-url";

  private final ImageUrlMapper mapper;
  private final ImageUrlRepository repository;
  private final FoodPlaceRepository foodPlaceRepository;
  private final StayPlaceRepository stayPlaceRepository;
  private final AreaPlaceRepository areaPlaceRepository;
  private final PointLocationRepository pointLocationRepository;

  public ImageUrlServiceImpl(ImageUrlMapper mapper, ImageUrlRepository repository, FoodPlaceRepository foodPlaceRepository,
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
  public ImageUrlDTO create(ImageUrlDTO imageUrlDTO) {
    log.debug("Request to get create ImageUrl {}", imageUrlDTO);
    nullCheck(imageUrlDTO);
    ImageUrl imageUrl = mapper.toEntity(imageUrlDTO);
    imageUrl.setId(null);
    return mapper.toDto(repository.save(imageUrl));
  }

  @Override
  public ImageUrlDTO update(ImageUrlDTO imageUrlDTO) {
    log.debug("Request to get update ImageUrl {}", imageUrlDTO);
    if(imageUrlDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<ImageUrl> tmp = repository.findById(imageUrlDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    nullCheck(imageUrlDTO);
    ImageUrl imageUrl = mapper.toEntity(imageUrlDTO);
    return mapper.toDto(repository.save(imageUrl));
  }

  private void nullCheck(ImageUrlDTO imageUrlDTO) {
    if(imageUrlDTO.getAreaPlaceId() != null && !areaPlaceRepository.existsById(imageUrlDTO.getAreaPlaceId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "area-place");
    }
    if(imageUrlDTO.getFoodPlaceId() != null && !foodPlaceRepository.existsById(imageUrlDTO.getFoodPlaceId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "food-place");
    }
    if(imageUrlDTO.getStayPlaceId() != null && !stayPlaceRepository.existsById(imageUrlDTO.getStayPlaceId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "stay-place");
    }
    if(imageUrlDTO.getPointLocationId() != null && !pointLocationRepository.existsById(imageUrlDTO.getPointLocationId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "point-location");
    }
    if(imageUrlDTO.getFoodPlaceId() == null && imageUrlDTO.getAreaPlaceId() == null && imageUrlDTO.getStayPlaceId() == null
        && imageUrlDTO.getPointLocationId() == null) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "food-place/area-place/stay-place/point-location");
    }
  }

  @Override
  public List<ImageUrlDTO> findAll() {
    log.debug("Request to get all ImageUrls");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<ImageUrlDTO> findOne(Long id) {
    log.debug("Request to get ImageUrl {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete ImageUrl {}", id);
    Optional<ImageUrl> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    repository.deleteById(id);
  }

  @Override
  public List<ImageUrlDTO> findAllOfAreaPlace(Long id) {
    return repository.findAllByAreaPlaceId(id).stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<ImageUrlDTO> findAllOfFoodPlace(Long id) {
    return repository.findAllByFoodPlaceId(id).stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<ImageUrlDTO> findAllOfStayPlace(Long id) {
    return repository.findAllByStayPlaceId(id).stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public List<ImageUrlDTO> findAllOfPointLocation(Long id) {
    return repository.findAllByPointLocationId(id).stream().map(mapper::toDto).collect(Collectors.toList());
  }
}
