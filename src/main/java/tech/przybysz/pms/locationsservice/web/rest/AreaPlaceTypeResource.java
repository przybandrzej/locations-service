package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.AreaPlaceService;
import tech.przybysz.pms.locationsservice.service.AreaPlaceTypeService;
import tech.przybysz.pms.locationsservice.service.dto.AreaPlaceDTO;
import tech.przybysz.pms.locationsservice.service.dto.AreaPlaceTypeDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/area-place-types")
public class AreaPlaceTypeResource {

  private final Logger log = LoggerFactory.getLogger(AreaPlaceTypeResource.class);
  private static final String ENTITY_NAME = "area-place-type";
  @Value("${spring.application.name}")
  private String applicationName;

  private final AreaPlaceTypeService areaPlaceTypeService;
  private final AreaPlaceService areaPlaceService;

  public AreaPlaceTypeResource(AreaPlaceTypeService areaPlaceTypeService, AreaPlaceService areaPlaceService) {
    this.areaPlaceTypeService = areaPlaceTypeService;
    this.areaPlaceService = areaPlaceService;
  }

  @GetMapping
  public List<AreaPlaceTypeDTO> getAllAreaPlaceTypes() {
    log.debug("REST request to get all AreaPlaceTypes");
    return areaPlaceTypeService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<AreaPlaceTypeDTO> getAreaPlaceType(@PathVariable Long id) {
    log.debug("REST request to get AreaPlaceType {}", id);
    return ResponseUtil.wrapOrNotFound(areaPlaceTypeService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAreaPlaceType(@PathVariable Long id) {
    log.debug("REST request to delete AreaPlaceType {}", id);
    areaPlaceTypeService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<AreaPlaceTypeDTO> createAreaPlaceType(@RequestBody AreaPlaceTypeDTO areaPlaceTypeDTO) {
    log.debug("REST request to create AreaPlaceType {}", areaPlaceTypeDTO);
    AreaPlaceTypeDTO save = areaPlaceTypeService.create(areaPlaceTypeDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<AreaPlaceTypeDTO> updateAreaPlaceType(@RequestBody AreaPlaceTypeDTO areaPlaceTypeDTO) {
    log.debug("REST request to update AreaPlaceType {}", areaPlaceTypeDTO);
    AreaPlaceTypeDTO save = areaPlaceTypeService.update(areaPlaceTypeDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @GetMapping("/{id}/places")
  public List<AreaPlaceDTO> getAreaPlaceTypeAllPlaces(@PathVariable Long id) {
    log.debug("REST request to get all AreaPlaces of AreaPlaceType {}", id);
    return areaPlaceService.findAllOfType(id);
  }

}
