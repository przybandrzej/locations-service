package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.PointLocationService;
import tech.przybysz.pms.locationsservice.service.PointLocationTypeService;
import tech.przybysz.pms.locationsservice.service.dto.PointLocationDTO;
import tech.przybysz.pms.locationsservice.service.dto.PointLocationTypeDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/point-location-types")
public class PointLocationTypeResource {

  private final Logger log = LoggerFactory.getLogger(PointLocationTypeResource.class);
  private static final String ENTITY_NAME = "point-location-type";
  @Value("${spring.application.name}")
  private String applicationName;

  private final PointLocationTypeService pointLocationTypeService;
  private final PointLocationService pointLocationService;

  public PointLocationTypeResource(PointLocationTypeService pointLocationTypeService, PointLocationService pointLocationService) {
    this.pointLocationTypeService = pointLocationTypeService;
    this.pointLocationService = pointLocationService;
  }

  @GetMapping
  public List<PointLocationTypeDTO> getAllPointLocationTypes() {
    log.debug("REST request to get all PointLocationTypes");
    return pointLocationTypeService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PointLocationTypeDTO> getPointLocationType(@PathVariable Long id) {
    log.debug("REST request to get PointLocationType {}", id);
    return ResponseUtil.wrapOrNotFound(pointLocationTypeService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePointLocationType(@PathVariable Long id) {
    log.debug("REST request to delete PointLocationType {}", id);
    pointLocationTypeService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<PointLocationTypeDTO> createPointLocationType(@RequestBody PointLocationTypeDTO pointLocationTypeDTO) {
    log.debug("REST request to create PointLocationType {}", pointLocationTypeDTO);
    PointLocationTypeDTO save = pointLocationTypeService.create(pointLocationTypeDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<PointLocationTypeDTO> updatePointLocationType(@RequestBody PointLocationTypeDTO pointLocationTypeDTO) {
    log.debug("REST request to update PointLocationType {}", pointLocationTypeDTO);
    PointLocationTypeDTO save = pointLocationTypeService.update(pointLocationTypeDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @GetMapping("/{id}/places")
  public List<PointLocationDTO> getPointLocationTypeAllPlaces(@PathVariable Long id) {
    log.debug("REST request to get all PointLocations of PointLocationType {}", id);
    return pointLocationService.findAllOfType(id);
  }

}
