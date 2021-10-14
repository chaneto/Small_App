package workshop.music_workshop.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import workshop.music_workshop.model.binding.ArtistSeedBindingModel;
import workshop.music_workshop.model.entities.ArtistEntity;
import workshop.music_workshop.repositories.ArtistRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final Resource artistsFile;
    private final ArtistRepository artistRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    public ArtistServiceImpl(@Value("classpath:init/artists.json") Resource artistsFile, ArtistRepository artistRepository, ModelMapper mapper, Gson gson) {
        this.artistsFile = artistsFile;
        this.artistRepository = artistRepository;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public void seedArtist() throws IOException {
        if(artistRepository.count() == 0){
          ArtistSeedBindingModel[] artists = this.gson.fromJson(Files.readString(Path.of(artistsFile.getURI())), ArtistSeedBindingModel[].class);
          for(ArtistSeedBindingModel artist: artists){
              ArtistEntity artistEntity = this.mapper.map(artist, ArtistEntity.class);
              this.artistRepository.save(artistEntity);
          }
        }
    }

    @Override
    public List<String> getAllName() {
        return this.artistRepository.getAllName();
    }

    @Override
    public ArtistEntity findByName(String name) {
        return this.artistRepository.findByName(name);
    }
}
