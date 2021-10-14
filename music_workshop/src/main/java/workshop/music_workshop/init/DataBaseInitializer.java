package workshop.music_workshop.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import workshop.music_workshop.services.ArtistService;
import workshop.music_workshop.services.UserRoleService;
import workshop.music_workshop.services.UserService;

@Component
public class DataBaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final ArtistService artistService;

    public DataBaseInitializer(UserService userService, UserRoleService userRoleService, ArtistService artistService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.artistService = artistService;
    }


    @Override
    public void run(String... args) throws Exception {

        this.userRoleService.seedUsersRoleEntity();
        this.userService.seedUserEntity();
        this.artistService.seedArtist();
    }
}
