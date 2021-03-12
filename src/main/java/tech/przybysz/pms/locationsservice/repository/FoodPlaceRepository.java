package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.FoodPlace;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public interface FoodPlaceRepository extends JpaRepository<FoodPlace, Long> {

  Collection<FoodPlace> findAllByAddressId(Long addressId);
}
