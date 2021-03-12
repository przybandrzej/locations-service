package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.AreaPlaceDTO;

import java.util.List;
import java.util.Optional;

public interface AreaPlaceService {

  AreaPlaceDTO create(AreaPlaceDTO areaPlaceDTO);

  AreaPlaceDTO update(AreaPlaceDTO areaPlaceDTO);

  List<AreaPlaceDTO> findAll();

  Optional<AreaPlaceDTO> findOne(Long id);

  void delete(Long id);
}
