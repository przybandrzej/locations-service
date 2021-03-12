package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.FoodPlaceService;
import tech.przybysz.pms.locationsservice.service.FoodPlaceTypeService;
import tech.przybysz.pms.locationsservice.service.dto.FoodPlaceDTO;
import tech.przybysz.pms.locationsservice.service.dto.FoodPlaceTypeDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/food-place-types")
public class FoodPlaceTypeResource {

  private final Logger log = LoggerFactory.getLogger(FoodPlaceTypeResource.class);
  private static final String ENTITY_NAME = "food-place-type";
  @Value("${spring.application.name}")
  private String applicationName;

  private final FoodPlaceTypeService foodPlaceTypeService;
  private final FoodPlaceService foodPlaceService;

  public FoodPlaceTypeResource(FoodPlaceTypeService foodPlaceTypeService, FoodPlaceService foodPlaceService) {
    this.foodPlaceTypeService = foodPlaceTypeService;
    this.foodPlaceService = foodPlaceService;
  }

  @GetMapping
  public List<FoodPlaceTypeDTO> getAllFoodPlaceTypes() {
    log.debug("REST request to get all FoodPlaceTypes");
    return foodPlaceTypeService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<FoodPlaceTypeDTO> getFoodPlaceType(@PathVariable Long id) {
    log.debug("REST request to get FoodPlaceType {}", id);
    return ResponseUtil.wrapOrNotFound(foodPlaceTypeService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFoodPlaceType(@PathVariable Long id) {
    log.debug("REST request to delete FoodPlaceType {}", id);
    foodPlaceTypeService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<FoodPlaceTypeDTO> createFoodPlaceType(@RequestBody FoodPlaceTypeDTO foodPlaceTypeDTO) {
    log.debug("REST request to create FoodPlaceType {}", foodPlaceTypeDTO);
    FoodPlaceTypeDTO save = foodPlaceTypeService.create(foodPlaceTypeDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<FoodPlaceTypeDTO> updateFoodPlaceType(@RequestBody FoodPlaceTypeDTO foodPlaceTypeDTO) {
    log.debug("REST request to update FoodPlaceType {}", foodPlaceTypeDTO);
    FoodPlaceTypeDTO save = foodPlaceTypeService.update(foodPlaceTypeDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @GetMapping("/{id}/places")
  public List<FoodPlaceDTO> getFoodPlaceTypeAllPlaces(@PathVariable Long id) {
    log.debug("REST request to get all FoodPlaces of FoodPlaceType {}", id);
    return foodPlaceService.findAllOfType(id);
  }

}
