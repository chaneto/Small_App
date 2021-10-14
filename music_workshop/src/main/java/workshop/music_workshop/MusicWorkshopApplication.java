package workshop.music_workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MusicWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicWorkshopApplication.class, args);
    }


}
