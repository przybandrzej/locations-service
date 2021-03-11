package tech.przybysz.pms.locationsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.przybysz.pms.locationsservice.domain.AreaPlaceType;
import tech.przybysz.pms.locationsservice.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
