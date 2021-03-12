package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.StayPlaceTypeDTO;

import java.util.List;
import java.util.Optional;

public interface StayPlaceTypeService {

  StayPlaceTypeDTO create(StayPlaceTypeDTO stayPlaceTypeDTO);

  StayPlaceTypeDTO update(StayPlaceTypeDTO stayPlaceTypeDTO);

  List<StayPlaceTypeDTO> findAll();

  Optional<StayPlaceTypeDTO> findOne(Long id);

  void delete(Long id);
}
