package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {

  AddressDTO create(AddressDTO addressDTO);

  AddressDTO update(AddressDTO addressDTO);

  List<AddressDTO> findAll();

  Optional<AddressDTO> findOne(Long id);

  void delete(Long id);

  List<AddressDTO> findAllOfCityTown(Long id);
}
