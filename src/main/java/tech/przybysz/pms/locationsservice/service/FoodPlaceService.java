package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.FoodPlaceDTO;

import java.util.List;
import java.util.Optional;

public interface FoodPlaceService {

  FoodPlaceDTO create(FoodPlaceDTO foodPlaceDTO);

  FoodPlaceDTO update(FoodPlaceDTO foodPlaceDTO);

  List<FoodPlaceDTO> findAll();

  Optional<FoodPlaceDTO> findOne(Long id);

  void delete(Long id);
}
