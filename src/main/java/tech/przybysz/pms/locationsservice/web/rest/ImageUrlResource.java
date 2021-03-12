package tech.przybysz.pms.locationsservice.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.przybysz.pms.locationsservice.service.ImageUrlService;
import tech.przybysz.pms.locationsservice.service.dto.ImageUrlDTO;
import tech.przybysz.pms.locationsservice.web.rest.util.HeaderUtil;
import tech.przybysz.pms.locationsservice.web.rest.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/imageUrls")
public class ImageUrlResource {

  private final Logger log = LoggerFactory.getLogger(ImageUrlResource.class);
  private static final String ENTITY_NAME = "image-url";
  @Value("${spring.application.name}")
  private String applicationName;

  private final ImageUrlService imageUrlService;

  public ImageUrlResource(ImageUrlService imageUrlService) {
    this.imageUrlService = imageUrlService;
  }

  @GetMapping
  public List<ImageUrlDTO> getAllImageUrls() {
    log.debug("REST request to get all ImageUrls");
    return imageUrlService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ImageUrlDTO> getImageUrl(@PathVariable Long id) {
    log.debug("REST request to get ImageUrl {}", id);
    return ResponseUtil.wrapOrNotFound(imageUrlService.findOne(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteImageUrl(@PathVariable Long id) {
    log.debug("REST request to delete ImageUrl {}", id);
    imageUrlService.delete(id);
    return ResponseEntity.noContent()
        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }

  @PostMapping
  public ResponseEntity<ImageUrlDTO> createImageUrl(@RequestBody ImageUrlDTO imageUrlDTO) {
    log.debug("REST request to create ImageUrl {}", imageUrlDTO);
    ImageUrlDTO save = imageUrlService.create(imageUrlDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }

  @PutMapping
  public ResponseEntity<ImageUrlDTO> updateImageUrl(@RequestBody ImageUrlDTO imageUrlDTO) {
    log.debug("REST request to update ImageUrl {}", imageUrlDTO);
    ImageUrlDTO save = imageUrlService.update(imageUrlDTO);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, save.getId().toString()))
        .body(save);
  }
}
