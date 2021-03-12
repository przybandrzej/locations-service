package tech.przybysz.pms.locationsservice.service;


import tech.przybysz.pms.locationsservice.service.dto.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {

  CommentDTO create(CommentDTO commentDTO);

  CommentDTO update(CommentDTO commentDTO);

  List<CommentDTO> findAll();

  Optional<CommentDTO> findOne(Long id);

  void delete(Long id);
}
