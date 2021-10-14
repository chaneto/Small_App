package workshop.music_workshop.services;

import workshop.music_workshop.model.entities.AlbumEntity;
import workshop.music_workshop.model.services.AlbumServiceModel;

import java.util.Optional;

public interface AlbumService {

    void createAlbum(AlbumServiceModel albumServiceModel);

    Optional<AlbumEntity> findById(Long id);

}
