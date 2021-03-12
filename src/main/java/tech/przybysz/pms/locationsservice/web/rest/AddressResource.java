package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.AddressService;
import tech.przybysz.pms.locationsservice.service.FoodPlaceService;
import tech.przybysz.pms.locationsservice.service.StayPlaceService;
import tech.przybysz.pms.locationsservice.service.dto.AddressDTO;
import tech.przybysz.pms.locationsservice.service.dto.FoodPlaceDTO;
import tech.przybysz.pms.locationsservice.service.dto.StayPlaceDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressResource {

  private final Logger log = LoggerFactory.getLogger(AddressResource.class);
  private static final String ENTITY_NAME = "address";
  @Value("${spring.application.name}")
  private String applicationName;

  private final AddressService addressService;
  private final FoodPlaceService foodPlaceService;
  private final StayPlaceService stayPlaceService;

  public AddressResource(AddressService addressService, FoodPlaceService foodPlaceService, StayPlaceService stayPlaceService) {
    this.addressService = addressService;
    this.foodPlaceService = foodPlaceService;
    this.stayPlaceService = stayPlaceService;
  }

  @GetMapping
  public List<AddressDTO> getAllAddresses() {
    log.debug("REST request to get all Addresses");
    return addressService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<AddressDTO> getAddress(@PathVariable Long id) {
    log.debug("REST request to get Address {}", id);
    return ResponseUtil.wrapOrNotFound(addressService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
    log.debug("REST request to delete Address {}", id);
    addressService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
    log.debug("REST request to create Address {}", addressDTO);
    AddressDTO save = addressService.create(addressDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO) {
    log.debug("REST request to update Address {}", addressDTO);
    AddressDTO save = addressService.update(addressDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @GetMapping("/{id}/food-places")
  public List<FoodPlaceDTO> getAddressAllFoodPlaces(@PathVariable Long id) {
    log.debug("REST request to get all FoodPlaces of Address {}", id);
    return foodPlaceService.findAllOfAddress(id);
  }

  @GetMapping("/{id}/stay-places")
  public List<StayPlaceDTO> getAddressAllStayPlaces(@PathVariable Long id) {
    log.debug("REST request to get all StayPlaces of Address {}", id);
    return stayPlaceService.findAllOfAddress(id);
  }
}
