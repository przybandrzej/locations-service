package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.CommentService;
import tech.przybysz.pms.locationsservice.service.PointLocationService;
import tech.przybysz.pms.locationsservice.service.ImageUrlService;
import tech.przybysz.pms.locationsservice.service.dto.CommentDTO;
import tech.przybysz.pms.locationsservice.service.dto.PointLocationDTO;
import tech.przybysz.pms.locationsservice.service.dto.ImageUrlDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/point-locations")
public class PointLocationResource {

  private final Logger log = LoggerFactory.getLogger(PointLocationResource.class);
  private static final String ENTITY_NAME = "point-location";
  @Value("${spring.application.name}")
  private String applicationName;

  private final PointLocationService pointLocationService;
  private final ImageUrlService imageUrlService;
  private final CommentService commentService;

  public PointLocationResource(PointLocationService pointLocationService, ImageUrlService imageUrlService,
                               CommentService commentService) {
    this.pointLocationService = pointLocationService;
    this.imageUrlService = imageUrlService;
    this.commentService = commentService;
  }

  @GetMapping
  public List<PointLocationDTO> getAllPointLocations() {
    log.debug("REST request to get all PointLocations");
    return pointLocationService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PointLocationDTO> getPointLocation(@PathVariable Long id) {
    log.debug("REST request to get PointLocation {}", id);
    return ResponseUtil.wrapOrNotFound(pointLocationService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePointLocation(@PathVariable Long id) {
    log.debug("REST request to delete PointLocation {}", id);
    pointLocationService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<PointLocationDTO> createPointLocation(@RequestBody PointLocationDTO pointLocationDTO) {
    log.debug("REST request to create PointLocation {}", pointLocationDTO);
    PointLocationDTO save = pointLocationService.create(pointLocationDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<PointLocationDTO> updatePointLocation(@RequestBody PointLocationDTO pointLocationDTO) {
    log.debug("REST request to update PointLocation {}", pointLocationDTO);
    PointLocationDTO save = pointLocationService.update(pointLocationDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @GetMapping("/{id}/comments")
  public List<CommentDTO> getPointLocationAllComments(@PathVariable Long id) {
    log.debug("REST request to get all Comments of PointLocation {}", id);
    return commentService.findAllOfPointLocation(id);
  }

  @GetMapping("/{id}/images")
  public List<ImageUrlDTO> getPointLocationAllImages(@PathVariable Long id) {
    log.debug("REST request to get all ImageUrl of PointLocation {}", id);
    return imageUrlService.findAllOfPointLocation(id);
  }
}
