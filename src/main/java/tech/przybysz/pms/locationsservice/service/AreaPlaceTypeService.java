package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.AreaPlaceTypeDTO;

import java.util.List;
import java.util.Optional;

public interface AreaPlaceTypeService {

  AreaPlaceTypeDTO create(AreaPlaceTypeDTO areaPlaceTypeDTO);

  AreaPlaceTypeDTO update(AreaPlaceTypeDTO areaPlaceTypeDTO);

  List<AreaPlaceTypeDTO> findAll();

  Optional<AreaPlaceTypeDTO> findOne(Long id);

  void delete(Long id);
}
