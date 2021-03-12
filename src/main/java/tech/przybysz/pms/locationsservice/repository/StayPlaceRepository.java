package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.StayPlace;

import java.util.Collection;

@Repository
public interface StayPlaceRepository extends JpaRepository<StayPlace, Long> {

  Collection<StayPlace> findAllByAddressId(Long addressId);

  Collection<StayPlace> findAllByTypeId(Long stayPlaceTypeId);
}
