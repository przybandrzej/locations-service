package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.CommentService;
import tech.przybysz.pms.locationsservice.service.StayPlaceService;
import tech.przybysz.pms.locationsservice.service.ImageUrlService;
import tech.przybysz.pms.locationsservice.service.dto.CommentDTO;
import tech.przybysz.pms.locationsservice.service.dto.StayPlaceDTO;
import tech.przybysz.pms.locationsservice.service.dto.ImageUrlDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/stay-places")
public class StayPlaceResource {

  private final Logger log = LoggerFactory.getLogger(StayPlaceResource.class);
  private static final String ENTITY_NAME = "stay-place";
  @Value("${spring.application.name}")
  private String applicationName;

  private final StayPlaceService stayPlaceService;
  private final ImageUrlService imageUrlService;
  private final CommentService commentService;

  public StayPlaceResource(StayPlaceService stayPlaceService, ImageUrlService imageUrlService,
                           CommentService commentService) {
    this.stayPlaceService = stayPlaceService;
    this.imageUrlService = imageUrlService;
    this.commentService = commentService;
  }

  @GetMapping
  public List<StayPlaceDTO> getAllStayPlaces() {
    log.debug("REST request to get all StayPlaces");
    return stayPlaceService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<StayPlaceDTO> getStayPlace(@PathVariable Long id) {
    log.debug("REST request to get StayPlace {}", id);
    return ResponseUtil.wrapOrNotFound(stayPlaceService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStayPlace(@PathVariable Long id) {
    log.debug("REST request to delete StayPlace {}", id);
    stayPlaceService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<StayPlaceDTO> createStayPlace(@RequestBody StayPlaceDTO stayPlaceDTO) {
    log.debug("REST request to create StayPlace {}", stayPlaceDTO);
    StayPlaceDTO save = stayPlaceService.create(stayPlaceDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<StayPlaceDTO> updateStayPlace(@RequestBody StayPlaceDTO stayPlaceDTO) {
    log.debug("REST request to update StayPlace {}", stayPlaceDTO);
    StayPlaceDTO save = stayPlaceService.update(stayPlaceDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @GetMapping("/{id}/comments")
  public List<CommentDTO> getStayPlaceAllComments(@PathVariable Long id) {
    log.debug("REST request to get all Comments of StayPlace {}", id);
    return commentService.findAllOfStayPlace(id);
  }

  @GetMapping("/{id}/images")
  public List<ImageUrlDTO> getStayPlaceAllImages(@PathVariable Long id) {
    log.debug("REST request to get all ImageUrl of StayPlace {}", id);
    return imageUrlService.findAllOfStayPlace(id);
  }
}
