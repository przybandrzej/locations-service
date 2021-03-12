package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.FoodPlace;
import tech.przybysz.pms.locationsservice.domain.Comment;
import tech.przybysz.pms.locationsservice.domain.ImageUrl;
import tech.przybysz.pms.locationsservice.domain.PointLocation;
import tech.przybysz.pms.locationsservice.repository.*;
import tech.przybysz.pms.locationsservice.service.FoodPlaceService;
import tech.przybysz.pms.locationsservice.service.dto.FoodPlaceDTO;
import tech.przybysz.pms.locationsservice.service.exception.DependencyEntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.FoodPlaceMapper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FoodPlaceServiceImpl implements FoodPlaceService {

  private final Logger log = LoggerFactory.getLogger(FoodPlaceServiceImpl.class);
  public static final String ENTITY_NAME = "food-place";

  private final FoodPlaceMapper mapper;
  private final FoodPlaceRepository repository;
  private final CommentRepository commentRepository;
  private final ImageUrlRepository imageUrlRepository;
  private final AddressRepository addressRepository;
  private final FoodPlaceTypeRepository foodPlaceTypeRepository;

  public FoodPlaceServiceImpl(FoodPlaceMapper mapper, FoodPlaceRepository repository, CommentRepository commentRepository,
                              ImageUrlRepository imageUrlRepository, AddressRepository addressRepository,
                              FoodPlaceTypeRepository foodPlaceTypeRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.commentRepository = commentRepository;
    this.imageUrlRepository = imageUrlRepository;
    this.addressRepository = addressRepository;
    this.foodPlaceTypeRepository = foodPlaceTypeRepository;
  }

  @Override
  public FoodPlaceDTO create(FoodPlaceDTO foodPlaceDTO) {
    log.debug("Request to get create FoodPlace {}", foodPlaceDTO);
    if(foodPlaceDTO.getAddressId() != null && !addressRepository.existsById(foodPlaceDTO.getAddressId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "address");
    }
    if(foodPlaceDTO.getTypeId() == null || !foodPlaceTypeRepository.existsById(foodPlaceDTO.getTypeId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "food-place-type");
    }

    FoodPlace foodPlace = mapper.toEntity(foodPlaceDTO);
    foodPlace.setId(null);
    foodPlace.setCreated(LocalDateTime.now());
    foodPlace.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(foodPlace));
  }

  @Override
  public FoodPlaceDTO update(FoodPlaceDTO foodPlaceDTO) {
    log.debug("Request to get update FoodPlace {}", foodPlaceDTO);
    if(foodPlaceDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<FoodPlace> tmp = repository.findById(foodPlaceDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    if(foodPlaceDTO.getAddressId() != null && !addressRepository.existsById(foodPlaceDTO.getAddressId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "address");
    }
    if(foodPlaceDTO.getTypeId() == null || !foodPlaceTypeRepository.existsById(foodPlaceDTO.getTypeId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "food-place-type");
    }

    FoodPlace foodPlace = mapper.toEntity(foodPlaceDTO);
    foodPlace.setCreated(tmp.get().getCreated());
    foodPlace.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(foodPlace));
  }

  @Override
  public List<FoodPlaceDTO> findAll() {
    log.debug("Request to get all FoodPlaces");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<FoodPlaceDTO> findOne(Long id) {
    log.debug("Request to get FoodPlace {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete FoodPlace {}", id);
    Optional<FoodPlace> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    commentRepository.deleteAll(tmp.get().getComments());
    imageUrlRepository.deleteAll(tmp.get().getImages());

    repository.deleteById(id);
  }
}
