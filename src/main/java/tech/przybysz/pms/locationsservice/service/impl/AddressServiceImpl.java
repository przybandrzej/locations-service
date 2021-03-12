package tech.przybysz.pms.locationsservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.przybysz.pms.locationsservice.domain.Address;
import tech.przybysz.pms.locationsservice.domain.FoodPlace;
import tech.przybysz.pms.locationsservice.domain.StayPlace;
import tech.przybysz.pms.locationsservice.repository.AddressRepository;
import tech.przybysz.pms.locationsservice.repository.CityTownSpotRepository;
import tech.przybysz.pms.locationsservice.repository.FoodPlaceRepository;
import tech.przybysz.pms.locationsservice.repository.StayPlaceRepository;
import tech.przybysz.pms.locationsservice.service.AddressService;
import tech.przybysz.pms.locationsservice.service.dto.AddressDTO;
import tech.przybysz.pms.locationsservice.service.exception.DependencyEntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.mapper.AddressMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

  private final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);
  public static final String ENTITY_NAME = "address";

  private final AddressMapper mapper;
  private final AddressRepository repository;
  private final FoodPlaceRepository foodPlaceRepository;
  private final StayPlaceRepository stayPlaceRepository;
  private final CityTownSpotRepository cityTownSpotRepository;

  public AddressServiceImpl(AddressMapper mapper, AddressRepository repository, FoodPlaceRepository foodPlaceRepository,
                            StayPlaceRepository stayPlaceRepository, CityTownSpotRepository cityTownSpotRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.foodPlaceRepository = foodPlaceRepository;
    this.stayPlaceRepository = stayPlaceRepository;
    this.cityTownSpotRepository = cityTownSpotRepository;
  }

  @Override
  public AddressDTO create(AddressDTO addressDTO) {
    log.debug("Request to get create Address {}", addressDTO);
    Address address = mapper.toEntity(addressDTO);
    address.setId(null);
    if(addressDTO.getCityTownId() == null || !cityTownSpotRepository.existsById(addressDTO.getCityTownId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "city-town-spot");
    }
    return mapper.toDto(repository.save(address));
  }

  @Override
  public AddressDTO update(AddressDTO addressDTO) {
    log.debug("Request to get update Address {}", addressDTO);
    if(addressDTO.getId() == null || !repository.existsById(addressDTO.getId())) {
      throw new EntityNotFoundException(ENTITY_NAME);
    }
    if(addressDTO.getCityTownId() == null || !cityTownSpotRepository.existsById(addressDTO.getCityTownId())) {
      throw new DependencyEntityNotFoundException(ENTITY_NAME, "city-town-spot");
    }
    Address address = mapper.toEntity(addressDTO);
    return mapper.toDto(repository.save(address));
  }

  @Override
  public List<AddressDTO> findAll() {
    log.debug("Request to get all Addresses");
    return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Override
  public Optional<AddressDTO> findOne(Long id) {
    log.debug("Request to get Address {}", id);
    return repository.findById(id).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Address {}", id);
    Optional<Address> tmp = repository.findById(id);
    if(tmp.isEmpty()) {
      return;
    }
    Collection<FoodPlace> food = foodPlaceRepository.findAllByAddressId(id);
    food.forEach(it -> it.setAddress(null));
    foodPlaceRepository.saveAll(food);
    Collection<StayPlace> stay = stayPlaceRepository.findAllByAddressId(id);
    stay.forEach(it -> it.setAddress(null));
    stayPlaceRepository.saveAll(stay);

    repository.deleteById(id);
  }
}
