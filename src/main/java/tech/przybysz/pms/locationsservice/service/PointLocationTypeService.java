package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.PointLocationTypeDTO;

import java.util.List;
import java.util.Optional;

public interface PointLocationTypeService {

  PointLocationTypeDTO create(PointLocationTypeDTO pointLocationTypeDTO);

  PointLocationTypeDTO update(PointLocationTypeDTO pointLocationTypeDTO);

  List<PointLocationTypeDTO> findAll();

  Optional<PointLocationTypeDTO> findOne(Long id);

  void delete(Long id);
}
