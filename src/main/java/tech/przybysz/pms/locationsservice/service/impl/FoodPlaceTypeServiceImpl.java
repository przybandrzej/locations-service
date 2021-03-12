package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.FoodPlace;
import tech.przybysz.pms.locationsservice.domain.FoodPlaceType;
import tech.przybysz.pms.locationsservice.repository.FoodPlaceRepository;
import tech.przybysz.pms.locationsservice.repository.FoodPlaceTypeRepository;
import tech.przybysz.pms.locationsservice.service.FoodPlaceTypeService;
import tech.przybysz.pms.locationsservice.service.dto.FoodPlaceTypeDTO;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.FoodPlaceTypeMapper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FoodPlaceTypeServiceImpl implements FoodPlaceTypeService {

  private final Logger log = LoggerFactory.getLogger(FoodPlaceTypeServiceImpl.class);
  public static final String ENTITY_NAME = "food-place-type";

  private final FoodPlaceTypeMapper mapper;
  private final FoodPlaceTypeRepository repository;
  private final FoodPlaceRepository foodPlaceRepository;

  public FoodPlaceTypeServiceImpl(FoodPlaceTypeMapper mapper, FoodPlaceTypeRepository repository, FoodPlaceRepository foodPlaceRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.foodPlaceRepository = foodPlaceRepository;
  }

  @Override
  public FoodPlaceTypeDTO create(FoodPlaceTypeDTO foodPlaceTypeDTO) {
    log.debug("Request to get create FoodPlaceType {}", foodPlaceTypeDTO);

    FoodPlaceType foodPlaceType = mapper.toEntity(foodPlaceTypeDTO);
    foodPlaceType.setId(null);
    foodPlaceType.setCreated(LocalDateTime.now());
    foodPlaceType.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(foodPlaceType));
  }

  @Override
  public FoodPlaceTypeDTO update(FoodPlaceTypeDTO foodPlaceTypeDTO) {
    log.debug("Request to get update FoodPlaceType {}", foodPlaceTypeDTO);
    if(foodPlaceTypeDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<FoodPlaceType> tmp = repository.findById(foodPlaceTypeDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }

    FoodPlaceType foodPlaceType = mapper.toEntity(foodPlaceTypeDTO);
    foodPlaceType.setCreated(tmp.get().getCreated());
    foodPlaceType.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(foodPlaceType));
  }

  @Override
  public List<FoodPlaceTypeDTO> findAll() {
    log.debug("Request to get all FoodPlaceTypes");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<FoodPlaceTypeDTO> findOne(Long id) {
    log.debug("Request to get FoodPlaceType {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete FoodPlaceType {}", id);
    Optional<FoodPlaceType> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    Collection<FoodPlace> places = foodPlaceRepository.findAllByTypeId(id);
    places.forEach(it -> it.setType(null));
    foodPlaceRepository.saveAll(places);

    repository.deleteById(id);
  }
}
