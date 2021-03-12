package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.ImageUrl;

import java.util.Collection;

@Repository
public interface ImageUrlRepository extends JpaRepository<ImageUrl, Long> {

  Collection<ImageUrl> findAllByAreaPlaceId(Long areaPlaceId);

  Collection<ImageUrl> findAllByFoodPlaceId(Long id);

  Collection<ImageUrl> findAllByStayPlaceId(Long id);

  Collection<ImageUrl> findAllByPointLocationId(Long id);
}
