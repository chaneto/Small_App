package workshop.music_workshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import workshop.music_workshop.model.entities.AlbumEntity;
import workshop.music_workshop.model.entities.ArtistEntity;
import workshop.music_workshop.model.entities.UserEntity;
import workshop.music_workshop.model.services.AlbumServiceModel;
import workshop.music_workshop.repositories.AlbumRepository;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final ModelMapper mapper;
    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final ArtistService artistService;

    public AlbumServiceImpl(ModelMapper mapper, AlbumRepository albumRepository, UserService userService, ArtistService artistService) {
        this.mapper = mapper;
        this.albumRepository = albumRepository;
        this.userService = userService;
        this.artistService = artistService;
    }

    @Override
    public void createAlbum(AlbumServiceModel albumServiceModel) {
        AlbumEntity albumEntity = this.mapper.map(albumServiceModel, AlbumEntity.class);
        UserEntity userEntity = this.userService.findByUsername(albumServiceModel.getUser());
        if(userEntity == null){
            throw new IllegalArgumentException("Creator cloud be not found");
        }else {
            albumEntity.setUserEntity(userEntity);
        }
        ArtistEntity artistEntity = this.artistService.findByName(albumServiceModel.getArtist());
        albumEntity.setArtistEntity(artistEntity);
        this.albumRepository.save(albumEntity);
    }

    public Optional<AlbumEntity> findById(Long id){
       return this.albumRepository.findById(id);
    }
}
