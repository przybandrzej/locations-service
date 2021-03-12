package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.CityTownSpotDTO;

import java.util.List;
import java.util.Optional;

public interface CityTownSpotService {

  CityTownSpotDTO create(CityTownSpotDTO cityTownSpotDTO);

  CityTownSpotDTO update(CityTownSpotDTO cityTownSpotDTO);

  List<CityTownSpotDTO> findAll();

  Optional<CityTownSpotDTO> findOne(Long id);

  void delete(Long id);
}
