package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.StayPlace;
import tech.przybysz.pms.locationsservice.repository.*;
import tech.przybysz.pms.locationsservice.service.StayPlaceService;
import tech.przybysz.pms.locationsservice.service.dto.StayPlaceDTO;
import tech.przybysz.pms.locationsservice.service.exception.DependencyEntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.StayPlaceMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StayPlaceServiceImpl implements StayPlaceService {

  private final Logger log = LoggerFactory.getLogger(StayPlaceServiceImpl.class);
  public static final String ENTITY_NAME = "stay-place";

  private final StayPlaceMapper mapper;
  private final StayPlaceRepository repository;
  private final CommentRepository commentRepository;
  private final ImageUrlRepository imageUrlRepository;
  private final AddressRepository addressRepository;
  private final StayPlaceTypeRepository stayPlaceTypeRepository;

  public StayPlaceServiceImpl(StayPlaceMapper mapper, StayPlaceRepository repository, CommentRepository commentRepository,
                              ImageUrlRepository imageUrlRepository, AddressRepository addressRepository,
                              StayPlaceTypeRepository stayPlaceTypeRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.commentRepository = commentRepository;
    this.imageUrlRepository = imageUrlRepository;
    this.addressRepository = addressRepository;
    this.stayPlaceTypeRepository = stayPlaceTypeRepository;
  }

  @Override
  public StayPlaceDTO create(StayPlaceDTO stayPlaceDTO) {
    log.debug("Request to get create StayPlace {}", stayPlaceDTO);
    if(stayPlaceDTO.getAddressId() != null && !addressRepository.existsById(stayPlaceDTO.getAddressId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "address");
    }
    if(stayPlaceDTO.getTypeId() == null || !stayPlaceTypeRepository.existsById(stayPlaceDTO.getTypeId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "stay-place-type");
    }

    StayPlace stayPlace = mapper.toEntity(stayPlaceDTO);
    stayPlace.setId(null);
    stayPlace.setCreated(LocalDateTime.now());
    stayPlace.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(stayPlace));
  }

  @Override
  public StayPlaceDTO update(StayPlaceDTO stayPlaceDTO) {
    log.debug("Request to get update StayPlace {}", stayPlaceDTO);
    if(stayPlaceDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<StayPlace> tmp = repository.findById(stayPlaceDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    if(stayPlaceDTO.getAddressId() != null && !addressRepository.existsById(stayPlaceDTO.getAddressId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "address");
    }
    if(stayPlaceDTO.getTypeId() == null || !stayPlaceTypeRepository.existsById(stayPlaceDTO.getTypeId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "stay-place-type");
    }

    StayPlace stayPlace = mapper.toEntity(stayPlaceDTO);
    stayPlace.setCreated(tmp.get().getCreated());
    stayPlace.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(stayPlace));
  }

  @Override
  public List<StayPlaceDTO> findAll() {
    log.debug("Request to get all StayPlaces");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<StayPlaceDTO> findOne(Long id) {
    log.debug("Request to get StayPlace {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete StayPlace {}", id);
    Optional<StayPlace> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    commentRepository.deleteAll(tmp.get().getComments());
    imageUrlRepository.deleteAll(tmp.get().getImages());

    repository.deleteById(id);
  }
}
