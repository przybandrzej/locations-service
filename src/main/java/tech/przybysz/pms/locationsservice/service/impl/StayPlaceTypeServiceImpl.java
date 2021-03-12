package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.StayPlace;
import tech.przybysz.pms.locationsservice.domain.StayPlaceType;
import tech.przybysz.pms.locationsservice.repository.StayPlaceRepository;
import tech.przybysz.pms.locationsservice.repository.StayPlaceTypeRepository;
import tech.przybysz.pms.locationsservice.service.StayPlaceTypeService;
import tech.przybysz.pms.locationsservice.service.dto.StayPlaceTypeDTO;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.StayPlaceTypeMapper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StayPlaceTypeServiceImpl implements StayPlaceTypeService {

  private final Logger log = LoggerFactory.getLogger(StayPlaceTypeServiceImpl.class);
  public static final String ENTITY_NAME = "stay-place-type";

  private final StayPlaceTypeMapper mapper;
  private final StayPlaceTypeRepository repository;
  private final StayPlaceRepository stayPlaceRepository;

  public StayPlaceTypeServiceImpl(StayPlaceTypeMapper mapper, StayPlaceTypeRepository repository, StayPlaceRepository stayPlaceRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.stayPlaceRepository = stayPlaceRepository;
  }

  @Override
  public StayPlaceTypeDTO create(StayPlaceTypeDTO stayPlaceTypeDTO) {
    log.debug("Request to get create StayPlaceType {}", stayPlaceTypeDTO);

    StayPlaceType stayPlaceType = mapper.toEntity(stayPlaceTypeDTO);
    stayPlaceType.setId(null);
    stayPlaceType.setCreated(LocalDateTime.now());
    stayPlaceType.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(stayPlaceType));
  }

  @Override
  public StayPlaceTypeDTO update(StayPlaceTypeDTO stayPlaceTypeDTO) {
    log.debug("Request to get update StayPlaceType {}", stayPlaceTypeDTO);
    if(stayPlaceTypeDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<StayPlaceType> tmp = repository.findById(stayPlaceTypeDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }

    StayPlaceType stayPlaceType = mapper.toEntity(stayPlaceTypeDTO);
    stayPlaceType.setCreated(tmp.get().getCreated());
    stayPlaceType.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(stayPlaceType));
  }

  @Override
  public List<StayPlaceTypeDTO> findAll() {
    log.debug("Request to get all StayPlaceTypes");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<StayPlaceTypeDTO> findOne(Long id) {
    log.debug("Request to get StayPlaceType {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete StayPlaceType {}", id);
    Optional<StayPlaceType> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    Collection<StayPlace> places = stayPlaceRepository.findAllByTypeId(id);
    places.forEach(it -> it.setType(null));
    stayPlaceRepository.saveAll(places);

    repository.deleteById(id);
  }
}
