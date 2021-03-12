package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.FoodPlaceTypeDTO;

import java.util.List;
import java.util.Optional;

public interface FoodPlaceTypeService {

  FoodPlaceTypeDTO create(FoodPlaceTypeDTO foodPlaceTypeDTO);

  FoodPlaceTypeDTO update(FoodPlaceTypeDTO foodPlaceTypeDTO);

  List<FoodPlaceTypeDTO> findAll();

  Optional<FoodPlaceTypeDTO> findOne(Long id);

  void delete(Long id);
}
