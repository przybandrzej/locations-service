package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.Comment;

import java.util.Collection;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  Collection<Comment> findAllByAreaPlaceId(Long areaPlaceId);

  Collection<Comment> findAllByFoodPlaceId(Long id);

  Collection<Comment> findAllByStayPlaceId(Long id);

  Collection<Comment> findAllByPointLocationId(Long id);
}
