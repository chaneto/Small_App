package workshop.music_workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workshop.music_workshop.model.entities.AlbumEntity;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
AlbumEntity deleteByName(String name);
}
