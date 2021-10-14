package workshop.music_workshop.web;

import workshop.music_workshop.model.entities.AlbumEntity;
import workshop.music_workshop.model.entities.ArtistEntity;
import workshop.music_workshop.model.entities.Genre;
import workshop.music_workshop.model.entities.UserEntity;
import workshop.music_workshop.repositories.AlbumRepository;
import workshop.music_workshop.repositories.ArtistRepository;
import workshop.music_workshop.repositories.LogRepository;
import workshop.music_workshop.repositories.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

class AlbumTestData {

    private long testAlbumId;

    private UserRepository userRepository;
    private ArtistRepository artistRepository;
    private AlbumRepository albumRepository;
    private LogRepository logRepository;

    public AlbumTestData(UserRepository userRepository,
                         ArtistRepository artistRepository,
                         AlbumRepository albumRepository, LogRepository logRepository) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.logRepository = logRepository;
    }

    public void init() {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("METALLICA");
        artistEntity.setCareerInformation("Some info about metallica");
        artistEntity = artistRepository.save(artistEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("pesho");
        userEntity.setPassword("xyz");
        userEntity.setFullname("petar petrov");
        userEntity = userRepository.save(userEntity);

        AlbumEntity justiceForAll = new AlbumEntity();
        justiceForAll.setName("JUSTICE FOR ALL");
        justiceForAll.setImageUrl("https://upload.wikimedia.org/wikipedia/en/b/bd/Metallica_-_...And_Justice_for_All_cover.jpg");
        justiceForAll.setVideoUrl("_fKAsvJrFes");
        justiceForAll.setDescription("Sample description");
        justiceForAll.setCopies(11);
        justiceForAll.setPrice(BigDecimal.TEN);
        justiceForAll.setReleaseDate(LocalDate.of(1988, 3, 3));//.atStartOfDay(ZoneId.systemDefault()).toInstant());
        justiceForAll.setGenre(Genre.METAL);
        justiceForAll.setArtistEntity(artistEntity);
        justiceForAll.setUserEntity(userEntity);

        justiceForAll = albumRepository.save(justiceForAll);

        AlbumEntity masterOfPuppets = new AlbumEntity();
        masterOfPuppets.setName("MASTER OF PUPPETS");
        masterOfPuppets.setImageUrl("https://upload.wikimedia.org/wikipedia/en/b/bd/Metallica_-_...And_Justice_for_All_cover.jpg");
        masterOfPuppets.setVideoUrl("_fKAsvJrFes");
        masterOfPuppets.setDescription("Sample description");
        masterOfPuppets.setCopies(111);
        masterOfPuppets. setPrice(BigDecimal.TEN);
        masterOfPuppets. setReleaseDate(LocalDate.of(1988, 3, 3));//.atStartOfDay(ZoneId.systemDefault()).toInstant());
        masterOfPuppets. setGenre(Genre.METAL);
        masterOfPuppets.setArtistEntity(artistEntity);
        masterOfPuppets. setUserEntity(userEntity);

        masterOfPuppets = albumRepository.save(masterOfPuppets);


        testAlbumId = justiceForAll.getId();
    }

    void cleanUp() {
        logRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();
    }

    public long getTestAlbumId() {
        return testAlbumId;
    }
}
