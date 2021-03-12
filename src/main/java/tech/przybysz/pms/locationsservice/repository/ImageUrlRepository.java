package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.ImageUrl;

import java.util.Collection;

@Repository
public interface ImageUrlRepository extends JpaRepository<ImageUrl, Long> {

  Collection<ImageUrl> findAllByAreaPlaceId(Long areaPlaceId);
}
