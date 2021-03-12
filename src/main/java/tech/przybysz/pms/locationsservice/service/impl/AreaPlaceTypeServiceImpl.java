package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.AreaPlace;
import tech.przybysz.pms.locationsservice.domain.AreaPlaceType;
import tech.przybysz.pms.locationsservice.repository.AreaPlaceRepository;
import tech.przybysz.pms.locationsservice.repository.AreaPlaceTypeRepository;
import tech.przybysz.pms.locationsservice.service.AreaPlaceTypeService;
import tech.przybysz.pms.locationsservice.service.dto.AreaPlaceTypeDTO;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.AreaPlaceTypeMapper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AreaPlaceTypeServiceImpl implements AreaPlaceTypeService {

  private final Logger log = LoggerFactory.getLogger(AreaPlaceTypeServiceImpl.class);
  public static final String ENTITY_NAME = "area-place-type";

  private final AreaPlaceTypeMapper mapper;
  private final AreaPlaceTypeRepository repository;
  private final AreaPlaceRepository areaPlaceRepository;

  public AreaPlaceTypeServiceImpl(AreaPlaceTypeMapper mapper, AreaPlaceTypeRepository repository, AreaPlaceRepository areaPlaceRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.areaPlaceRepository = areaPlaceRepository;
  }

  @Override
  public AreaPlaceTypeDTO create(AreaPlaceTypeDTO areaPlaceTypeDTO) {
    log.debug("Request to get create AreaPlaceType {}", areaPlaceTypeDTO);

    AreaPlaceType areaPlaceType = mapper.toEntity(areaPlaceTypeDTO);
    areaPlaceType.setId(null);
    areaPlaceType.setCreated(LocalDateTime.now());
    areaPlaceType.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(areaPlaceType));
  }

  @Override
  public AreaPlaceTypeDTO update(AreaPlaceTypeDTO areaPlaceTypeDTO) {
    log.debug("Request to get update AreaPlaceType {}", areaPlaceTypeDTO);
    if(areaPlaceTypeDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<AreaPlaceType> tmp = repository.findById(areaPlaceTypeDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }

    AreaPlaceType areaPlaceType = mapper.toEntity(areaPlaceTypeDTO);
    areaPlaceType.setCreated(tmp.get().getCreated());
    areaPlaceType.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(areaPlaceType));
  }

  @Override
  public List<AreaPlaceTypeDTO> findAll() {
    log.debug("Request to get all AreaPlaceTypes");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<AreaPlaceTypeDTO> findOne(Long id) {
    log.debug("Request to get AreaPlaceType {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete AreaPlaceType {}", id);
    Optional<AreaPlaceType> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    Collection<AreaPlace> places = areaPlaceRepository.findAllByTypeId(id);
    places.forEach(it -> it.setType(null));
    areaPlaceRepository.saveAll(places);

    repository.deleteById(id);
  }
}
