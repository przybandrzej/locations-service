package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.PointLocationDTO;

import java.util.List;
import java.util.Optional;

public interface PointLocationService {

  PointLocationDTO create(PointLocationDTO pointLocationDTO);

  PointLocationDTO update(PointLocationDTO pointLocationDTO);

  List<PointLocationDTO> findAll();

  Optional<PointLocationDTO> findOne(Long id);

  void delete(Long id);
}
