package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.PointLocation;
import tech.przybysz.pms.locationsservice.domain.PointLocationType;
import tech.przybysz.pms.locationsservice.repository.PointLocationRepository;
import tech.przybysz.pms.locationsservice.repository.PointLocationTypeRepository;
import tech.przybysz.pms.locationsservice.service.PointLocationTypeService;
import tech.przybysz.pms.locationsservice.service.dto.PointLocationTypeDTO;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.PointLocationTypeMapper;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PointLocationTypeServiceImpl implements PointLocationTypeService {

  private final Logger log = LoggerFactory.getLogger(PointLocationTypeServiceImpl.class);
  public static final String ENTITY_NAME = "point-location-type";

  private final PointLocationTypeMapper mapper;
  private final PointLocationTypeRepository repository;
  private final PointLocationRepository pointLocationRepository;

  public PointLocationTypeServiceImpl(PointLocationTypeMapper mapper, PointLocationTypeRepository repository, PointLocationRepository pointLocationRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.pointLocationRepository = pointLocationRepository;
  }

  @Override
  public PointLocationTypeDTO create(PointLocationTypeDTO pointLocationTypeDTO) {
    log.debug("Request to get create PointLocationType {}", pointLocationTypeDTO);
    PointLocationType pointLocationType = mapper.toEntity(pointLocationTypeDTO);
    pointLocationType.setId(null);
    pointLocationType.setCreated(LocalDateTime.now());
    pointLocationType.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(pointLocationType));
  }

  @Override
  public PointLocationTypeDTO update(PointLocationTypeDTO pointLocationTypeDTO) {
    log.debug("Request to get update PointLocationType {}", pointLocationTypeDTO);
    if(pointLocationTypeDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<PointLocationType> tmp = repository.findById(pointLocationTypeDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    PointLocationType pointLocationType = mapper.toEntity(pointLocationTypeDTO);
    pointLocationType.setCreated(tmp.get().getCreated());
    pointLocationType.setModified(LocalDateTime.now());
    return mapper.toDto(repository.save(pointLocationType));
  }

  @Override
  public List<PointLocationTypeDTO> findAll() {
    log.debug("Request to get all PointLocationTypes");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<PointLocationTypeDTO> findOne(Long id) {
    log.debug("Request to get PointLocationType {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete PointLocationType {}", id);
    Optional<PointLocationType> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    Collection<PointLocation> places = pointLocationRepository.findAllByTypeId(id);
    places.forEach(it -> it.setType(null));
    pointLocationRepository.saveAll(places);

    repository.deleteById(id);
  }
}
