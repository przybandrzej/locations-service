package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.CommentService;
import tech.przybysz.pms.locationsservice.service.dto.CommentDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentResource {

  private final Logger log = LoggerFactory.getLogger(CommentResource.class);
  private static final String ENTITY_NAME = "comment";
  @Value("${spring.application.name}")
  private String applicationName;

  private final CommentService commentService;

  public CommentResource(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping
  public List<CommentDTO> getAllComments() {
    log.debug("REST request to get all Comments");
    return commentService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CommentDTO> getComment(@PathVariable Long id) {
    log.debug("REST request to get Comment {}", id);
    return ResponseUtil.wrapOrNotFound(commentService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
    log.debug("REST request to delete Comment {}", id);
    commentService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
    log.debug("REST request to create Comment {}", commentDTO);
    CommentDTO save = commentService.create(commentDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO) {
    log.debug("REST request to update Comment {}", commentDTO);
    CommentDTO save = commentService.update(commentDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }
}
