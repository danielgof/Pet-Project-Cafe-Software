package application.repo;

import application.domain.ModelPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PositionRepo extends JpaRepository<ModelPosition, Long> {
    @Query("select p from ModelPosition p where p.id=?1")
    ModelPosition find(Long id);
}
