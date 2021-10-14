package workshop.music_workshop.services;

import org.springframework.data.jpa.repository.Query;
import workshop.music_workshop.model.entities.ArtistEntity;

import java.io.IOException;
import java.util.List;

public interface ArtistService {

    void seedArtist() throws IOException;

    List<String> getAllName();

    ArtistEntity findByName(String name);
}
