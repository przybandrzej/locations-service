package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.CityTownSpot;

import java.util.Collection;

@Repository
public interface CityTownSpotRepository extends JpaRepository<CityTownSpot, Long> {

  Collection<CityTownSpot> findAllByParentId(Long parentId);
}
