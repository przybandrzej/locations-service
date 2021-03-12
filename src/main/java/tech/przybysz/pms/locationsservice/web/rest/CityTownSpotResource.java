package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.*;
import tech.przybysz.pms.locationsservice.service.dto.*;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/cities-towns-spots")
public class CityTownSpotResource {

  private final Logger log = LoggerFactory.getLogger(CityTownSpotResource.class);
  private static final String ENTITY_NAME = "city-town-spot";
  @Value("${spring.application.name}")
  private String applicationName;

  private final CityTownSpotService cityTownSpotService;
  private final AddressService addressService;
  private final AreaPlaceService areaPlaceService;

  public CityTownSpotResource(CityTownSpotService cityTownSpotService, AddressService addressService, AreaPlaceService areaPlaceService) {
    this.cityTownSpotService = cityTownSpotService;
    this.addressService = addressService;
    this.areaPlaceService = areaPlaceService;
  }

  @GetMapping
  public List<CityTownSpotDTO> getAllCityTownSpots() {
    log.debug("REST request to get all CityTownSpots");
    return cityTownSpotService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CityTownSpotDTO> getCityTownSpot(@PathVariable Long id) {
    log.debug("REST request to get CityTownSpot {}", id);
    return ResponseUtil.wrapOrNotFound(cityTownSpotService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCityTownSpot(@PathVariable Long id) {
    log.debug("REST request to delete CityTownSpot {}", id);
    cityTownSpotService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<CityTownSpotDTO> createCityTownSpot(@RequestBody CityTownSpotDTO cityTownSpotDTO) {
    log.debug("REST request to create CityTownSpot {}", cityTownSpotDTO);
    CityTownSpotDTO save = cityTownSpotService.create(cityTownSpotDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<CityTownSpotDTO> updateCityTownSpot(@RequestBody CityTownSpotDTO cityTownSpotDTO) {
    log.debug("REST request to update CityTownSpot {}", cityTownSpotDTO);
    CityTownSpotDTO save = cityTownSpotService.update(cityTownSpotDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @GetMapping("/{id}/addresses")
  public List<AddressDTO> getCityTownSpotAllAddresses(@PathVariable Long id) {
    log.debug("REST request to get all Addresses of CityTownSpot {}", id);
    return addressService.findAllOfCityTown(id);
  }

  @GetMapping("/{id}/area-places")
  public List<AreaPlaceDTO> getCityTownSpotAllAreaPlaces(@PathVariable Long id) {
    log.debug("REST request to get all AreaPlaces of CityTownSpot {}", id);
    return areaPlaceService.findAllOfSpot(id);
  }

  @GetMapping("/{id}/children")
  public List<CityTownSpotDTO> getCityTownSpotAllChildren(@PathVariable Long id) {
    log.debug("REST request to get all StayPlaces of CityTownSpot {}", id);
    return cityTownSpotService.findAllChildren(id);
  }
}
