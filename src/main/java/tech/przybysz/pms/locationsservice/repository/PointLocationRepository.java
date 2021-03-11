package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.PointLocation;

@Repository
public interface PointLocationRepository extends JpaRepository<PointLocation, Long> {
}
