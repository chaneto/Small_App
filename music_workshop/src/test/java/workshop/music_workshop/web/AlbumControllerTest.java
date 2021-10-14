package workshop.music_workshop.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import workshop.music_workshop.model.entities.Genre;
import workshop.music_workshop.repositories.AlbumRepository;
import workshop.music_workshop.repositories.ArtistRepository;
import workshop.music_workshop.repositories.LogRepository;
import workshop.music_workshop.repositories.UserRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AlbumControllerTest {

    private static final String ALBUM_CONTROLLER_PREFIX = "/albums";

    private long testAlbumId;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private LogRepository logRepository;

    private AlbumTestData testData;

    @BeforeEach
    public void setup() {
        testData = new AlbumTestData(
                userRepository,
                artistRepository,
                albumRepository,
                logRepository
        );
        testData.init();
        testAlbumId = testData.getTestAlbumId();
    }

    @AfterEach
    public void tearDown() {
        testData.cleanUp();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                ALBUM_CONTROLLER_PREFIX + "/details/{id}", testAlbumId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("details")).
                andExpect(model().attributeExists("album"));
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    void addAlbum() throws Exception {
        mockMvc.perform(post(ALBUM_CONTROLLER_PREFIX + "/add")
                .param("name", "test album").
                        param("genre", Genre.METAL.name()).
                        param("imageUrl", "http://example.com/image.png").
                        param("videoUrl", "_fKAsvJrFes").
                        param("description", "Description test").
                        param("copies", String.valueOf(123333)).
                        param("price", "10").
                        param("releaseDate", "2000-01-01").
                        param("artist", "METALLICA").
                        with(csrf())).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(3, albumRepository.count());
    }
}
