package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.ImageUrlDTO;

import java.util.List;
import java.util.Optional;

public interface ImageUrlService {

  ImageUrlDTO create(ImageUrlDTO imageUrlDTO);

  ImageUrlDTO update(ImageUrlDTO imageUrlDTO);

  List<ImageUrlDTO> findAll();

  Optional<ImageUrlDTO> findOne(Long id);

  void delete(Long id);

  List<ImageUrlDTO> findAllOfAreaPlace(Long id);

  List<ImageUrlDTO> findAllOfFoodPlace(Long id);

  List<ImageUrlDTO> findAllOfStayPlace(Long id);

  List<ImageUrlDTO> findAllOfPointLocation(Long id);
}
