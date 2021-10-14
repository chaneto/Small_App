package workshop.music_workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.music_workshop.model.entities.LogEntity;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {
}
