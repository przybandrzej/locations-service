package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.AreaPlace;

import java.util.Collection;

@Repository
public interface AreaPlaceRepository extends JpaRepository<AreaPlace, Long> {

  Collection<AreaPlace> findAllByTypeId(Long areaPlaceTypeId);

  Collection<AreaPlace> findAllBySpotId(Long spotId);
}
