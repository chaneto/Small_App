package workshop.music_workshop.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import workshop.music_workshop.model.entities.UserEntity;
import workshop.music_workshop.repositories.UserRepository;
import workshop.music_workshop.services.UserService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    UserEntity userEntity1;
    UserEntity userEntity2;
    UserEntity userEntity3;

    @Before
    public void setUp(){
        userEntity1 = new UserEntity();
        userEntity1.setUsername("Petko");
        userEntity1.setFullname("Petko Petkov");
        userEntity1.setPassword("12345");
        userEntity2 = new UserEntity();
        userEntity2.setUsername("Gosho");
        userEntity2.setFullname("Gosho Goshev");
        userEntity2.setPassword("54321");
        userEntity3 = new UserEntity();
        userEntity3.setUsername("Tencho");
        userEntity3.setFullname("Tencho Tenchev");
        userEntity3.setPassword("11111");

        this.userRepository.save(userEntity1);
        this.userRepository.save(userEntity2);
        this.userRepository.save(userEntity3);

    }

    @Test
    public void login() throws Exception {
        this.mockMvc
                .perform(get("/users/login"))
                .andExpect(view().name("login"));
        if(this.userRepository.count() > 0){
            this.userRepository.deleteAll();
        }
    }


    @Test
    //@WithMockUser
    public void register() throws Exception {
        this.mockMvc
                .perform(get("/users/register"))
                .andExpect(view().name("register"));
        if(this.userRepository.count() > 0){
            this.userRepository.deleteAll();
        }
    }

}
