package workshop.music_workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workshop.music_workshop.model.entities.ArtistEntity;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    ArtistEntity findByName(String name);

    @Query("select a.name from ArtistEntity as a")
    List<String> getAllName();
}
