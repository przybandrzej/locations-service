package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.CommentService;
import tech.przybysz.pms.locationsservice.service.FoodPlaceService;
import tech.przybysz.pms.locationsservice.service.ImageUrlService;
import tech.przybysz.pms.locationsservice.service.dto.CommentDTO;
import tech.przybysz.pms.locationsservice.service.dto.FoodPlaceDTO;
import tech.przybysz.pms.locationsservice.service.dto.ImageUrlDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/food-places")
public class FoodPlaceResource {

  private final Logger log = LoggerFactory.getLogger(FoodPlaceResource.class);
  private static final String ENTITY_NAME = "food-place";
  @Value("${spring.application.name}")
  private String applicationName;

  private final FoodPlaceService foodPlaceService;
  private final ImageUrlService imageUrlService;
  private final CommentService commentService;

  public FoodPlaceResource(FoodPlaceService foodPlaceService, ImageUrlService imageUrlService,
                           CommentService commentService) {
    this.foodPlaceService = foodPlaceService;
    this.imageUrlService = imageUrlService;
    this.commentService = commentService;
  }

  @GetMapping
  public List<FoodPlaceDTO> getAllFoodPlaces() {
    log.debug("REST request to get all FoodPlaces");
    return foodPlaceService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<FoodPlaceDTO> getFoodPlace(@PathVariable Long id) {
    log.debug("REST request to get FoodPlace {}", id);
    return ResponseUtil.wrapOrNotFound(foodPlaceService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFoodPlace(@PathVariable Long id) {
    log.debug("REST request to delete FoodPlace {}", id);
    foodPlaceService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<FoodPlaceDTO> createFoodPlace(@RequestBody FoodPlaceDTO foodPlaceDTO) {
    log.debug("REST request to create FoodPlace {}", foodPlaceDTO);
    FoodPlaceDTO save = foodPlaceService.create(foodPlaceDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<FoodPlaceDTO> updateFoodPlace(@RequestBody FoodPlaceDTO foodPlaceDTO) {
    log.debug("REST request to update FoodPlace {}", foodPlaceDTO);
    FoodPlaceDTO save = foodPlaceService.update(foodPlaceDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @GetMapping("/{id}/comments")
  public List<CommentDTO> getFoodPlaceAllComments(@PathVariable Long id) {
    log.debug("REST request to get all Comments of FoodPlace {}", id);
    return commentService.findAllOfFoodPlace(id);
  }

  @GetMapping("/{id}/images")
  public List<ImageUrlDTO> getFoodPlaceAllImages(@PathVariable Long id) {
    log.debug("REST request to get all ImageUrl of FoodPlace {}", id);
    return imageUrlService.findAllOfFoodPlace(id);
  }
}
