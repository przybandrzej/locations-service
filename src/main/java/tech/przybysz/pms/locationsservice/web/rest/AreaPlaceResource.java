package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.AreaPlaceService;
import tech.przybysz.pms.locationsservice.service.CommentService;
import tech.przybysz.pms.locationsservice.service.ImageUrlService;
import tech.przybysz.pms.locationsservice.service.PointLocationService;
import tech.przybysz.pms.locationsservice.service.dto.AreaPlaceDTO;
import tech.przybysz.pms.locationsservice.service.dto.CommentDTO;
import tech.przybysz.pms.locationsservice.service.dto.ImageUrlDTO;
import tech.przybysz.pms.locationsservice.service.dto.PointLocationDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/area-places")
public class AreaPlaceResource {

  private final Logger log = LoggerFactory.getLogger(AreaPlaceResource.class);
  private static final String ENTITY_NAME = "area-place";
  @Value("${spring.application.name}")
  private String applicationName;

  private final AreaPlaceService areaPlaceService;
  private final PointLocationService pointLocationService;
  private final ImageUrlService imageUrlService;
  private final CommentService commentService;

  public AreaPlaceResource(AreaPlaceService areaPlaceService, PointLocationService pointLocationService, ImageUrlService imageUrlService, CommentService commentService) {
    this.areaPlaceService = areaPlaceService;
    this.pointLocationService = pointLocationService;
    this.imageUrlService = imageUrlService;
    this.commentService = commentService;
  }

  @GetMapping
  public List<AreaPlaceDTO> getAllAreaPlaces() {
    log.debug("REST request to get all AreaPlaces");
    return areaPlaceService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<AreaPlaceDTO> getAreaPlace(@PathVariable Long id) {
    log.debug("REST request to get AreaPlace {}", id);
    return ResponseUtil.wrapOrNotFound(areaPlaceService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAreaPlace(@PathVariable Long id) {
    log.debug("REST request to delete AreaPlace {}", id);
    areaPlaceService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<AreaPlaceDTO> createAreaPlace(@RequestBody AreaPlaceDTO areaPlaceDTO) {
    log.debug("REST request to create AreaPlace {}", areaPlaceDTO);
    AreaPlaceDTO save = areaPlaceService.create(areaPlaceDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<AreaPlaceDTO> updateAreaPlace(@RequestBody AreaPlaceDTO areaPlaceDTO) {
    log.debug("REST request to update AreaPlace {}", areaPlaceDTO);
    AreaPlaceDTO save = areaPlaceService.update(areaPlaceDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @GetMapping("/{id}/comments")
  public List<CommentDTO> getAreaPlaceAllComments(@PathVariable Long id) {
    log.debug("REST request to get all Comments of AreaPlace {}", id);
    return commentService.findAllOfAreaPlace(id);
  }

  @GetMapping("/{id}/images")
  public List<ImageUrlDTO> getAreaPlaceAllImages(@PathVariable Long id) {
    log.debug("REST request to get all ImageUrl of AreaPlace {}", id);
    return imageUrlService.findAllOfAreaPlace(id);
  }

  @GetMapping("/{id}/point-locations")
  public List<PointLocationDTO> getAreaPlaceAllPointLocations(@PathVariable Long id) {
    log.debug("REST request to get all PointLocations of AreaPlace {}", id);
    return pointLocationService.findAllOfAreaPlace(id);
  }
}
