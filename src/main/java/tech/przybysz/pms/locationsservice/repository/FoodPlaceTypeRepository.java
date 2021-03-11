package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.AreaPlaceType;
import tech.przybysz.pms.locationsservice.domain.FoodPlaceType;

@Repository
public interface FoodPlaceTypeRepository extends JpaRepository<FoodPlaceType, Long> {
}