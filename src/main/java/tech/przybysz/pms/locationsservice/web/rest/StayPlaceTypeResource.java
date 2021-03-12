package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.StayPlaceService;
import tech.przybysz.pms.locationsservice.service.StayPlaceTypeService;
import tech.przybysz.pms.locationsservice.service.dto.StayPlaceDTO;
import tech.przybysz.pms.locationsservice.service.dto.StayPlaceTypeDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/stay-place-types")
public class StayPlaceTypeResource {

  private final Logger log = LoggerFactory.getLogger(StayPlaceTypeResource.class);
  private static final String ENTITY_NAME = "stay-place-type";
  @Value("${spring.application.name}")
  private String applicationName;

  private final StayPlaceTypeService stayPlaceTypeService;
  private final StayPlaceService stayPlaceService;

  public StayPlaceTypeResource(StayPlaceTypeService stayPlaceTypeService, StayPlaceService stayPlaceService) {
    this.stayPlaceTypeService = stayPlaceTypeService;
    this.stayPlaceService = stayPlaceService;
  }

  @GetMapping
  public List<StayPlaceTypeDTO> getAllStayPlaceTypes() {
    log.debug("REST request to get all StayPlaceTypes");
    return stayPlaceTypeService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<StayPlaceTypeDTO> getStayPlaceType(@PathVariable Long id) {
    log.debug("REST request to get StayPlaceType {}", id);
    return ResponseUtil.wrapOrNotFound(stayPlaceTypeService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStayPlaceType(@PathVariable Long id) {
    log.debug("REST request to delete StayPlaceType {}", id);
    stayPlaceTypeService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<StayPlaceTypeDTO> createStayPlaceType(@RequestBody StayPlaceTypeDTO stayPlaceTypeDTO) {
    log.debug("REST request to create StayPlaceType {}", stayPlaceTypeDTO);
    StayPlaceTypeDTO save = stayPlaceTypeService.create(stayPlaceTypeDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<StayPlaceTypeDTO> updateStayPlaceType(@RequestBody StayPlaceTypeDTO stayPlaceTypeDTO) {
    log.debug("REST request to update StayPlaceType {}", stayPlaceTypeDTO);
    StayPlaceTypeDTO save = stayPlaceTypeService.update(stayPlaceTypeDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @GetMapping("/{id}/places")
  public List<StayPlaceDTO> getStayPlaceTypeAllPlaces(@PathVariable Long id) {
    log.debug("REST request to get all StayPlaces of StayPlaceType {}", id);
    return stayPlaceService.findAllOfType(id);
  }

}
