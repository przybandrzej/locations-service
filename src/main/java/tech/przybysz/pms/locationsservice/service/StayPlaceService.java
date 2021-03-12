package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.StayPlaceDTO;

import java.util.List;
import java.util.Optional;

public interface StayPlaceService {

  StayPlaceDTO create(StayPlaceDTO stayPlaceDTO);

  StayPlaceDTO update(StayPlaceDTO stayPlaceDTO);

  List<StayPlaceDTO> findAll();

  Optional<StayPlaceDTO> findOne(Long id);

  void delete(Long id);
}
