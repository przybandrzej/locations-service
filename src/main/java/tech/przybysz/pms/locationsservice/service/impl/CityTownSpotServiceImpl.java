package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.Address;
import tech.przybysz.pms.locationsservice.domain.AreaPlace;
import tech.przybysz.pms.locationsservice.domain.CityTownSpot;
import tech.przybysz.pms.locationsservice.repository.AddressRepository;
import tech.przybysz.pms.locationsservice.repository.AreaPlaceRepository;
import tech.przybysz.pms.locationsservice.repository.CityTownSpotRepository;
import tech.przybysz.pms.locationsservice.service.CityTownSpotService;
import tech.przybysz.pms.locationsservice.service.dto.CityTownSpotDTO;
import tech.przybysz.pms.locationsservice.service.exception.DependencyEntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.CityTownSpotMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityTownSpotServiceImpl implements CityTownSpotService {

  private final Logger log = LoggerFactory.getLogger(CityTownSpotServiceImpl.class);
  public static final String ENTITY_NAME = "city-town-spot";

  private final CityTownSpotMapper mapper;
  private final CityTownSpotRepository repository;
  private final AddressRepository addressRepository;
  private final AreaPlaceRepository areaPlaceRepository;

  public CityTownSpotServiceImpl(CityTownSpotMapper mapper, CityTownSpotRepository repository,
                                 AddressRepository addressRepository, AreaPlaceRepository areaPlaceRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.addressRepository = addressRepository;
    this.areaPlaceRepository = areaPlaceRepository;
  }

  @Override
  public CityTownSpotDTO create(CityTownSpotDTO cityTownSpotDTO) {
    log.debug("Request to get create CityTownSpot {}", cityTownSpotDTO);
    if(cityTownSpotDTO.getParentId() != null && !repository.existsById(cityTownSpotDTO.getParentId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, ENTITY_NAME);
    }

    CityTownSpot cityTownSpot = mapper.toEntity(cityTownSpotDTO);
    cityTownSpot.setId(null);
    return mapper.toDto(repository.save(cityTownSpot));
  }

  @Override
  public CityTownSpotDTO update(CityTownSpotDTO cityTownSpotDTO) {
    log.debug("Request to get update CityTownSpot {}", cityTownSpotDTO);
    if(cityTownSpotDTO.getId() == null) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    Optional<CityTownSpot> tmp = repository.findById(cityTownSpotDTO.getId());
    if(tmp.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    if(cityTownSpotDTO.getParentId() != null && !repository.existsById(cityTownSpotDTO.getParentId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, ENTITY_NAME);
    }

    CityTownSpot cityTownSpot = mapper.toEntity(cityTownSpotDTO);
    return mapper.toDto(repository.save(cityTownSpot));
  }

  @Override
  public List<CityTownSpotDTO> findAll() {
    log.debug("Request to get all CityTownSpots");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<CityTownSpotDTO> findOne(Long id) {
    log.debug("Request to get CityTownSpot {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete CityTownSpot {}", id);
    Optional<CityTownSpot> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    Collection<Address> addresses = addressRepository.findAllByCityTownId(id);
    addressRepository.deleteAll(addresses);
    Collection<AreaPlace> areaPlaces = areaPlaceRepository.findAllBySpotId(id);
    areaPlaces.forEach(it -> it.setType(null));
    areaPlaceRepository.saveAll(areaPlaces);

    repository.deleteById(id);
  }

  @Override
  public List<CityTownSpotDTO> findAllChildren(Long parentId) {
    return repository.findAllByParentId(parentId).stream().map(mapper::toDto).collect(Collectors.toList());
  }
}
