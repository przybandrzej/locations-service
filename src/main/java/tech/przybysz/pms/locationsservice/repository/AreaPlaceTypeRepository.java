package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.AreaPlaceType;

@Repository
public interface AreaPlaceTypeRepository extends JpaRepository<AreaPlaceType, Long> {
}
