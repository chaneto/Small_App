package workshop.music_workshop.services;

import org.hibernate.mapping.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    private List<String> images = new ArrayList<>();

    private Logger LOGGER = LoggerFactory.getLogger(CarouselServiceImpl.class);

    @Autowired
    public CarouselServiceImpl(@Value("${images}") List<String> images, ImageShuffler imageShuffler) {
        System.out.println();
        this.images.addAll(images);
    }

    @PostConstruct
    public void afterInitialize(){
        if(images.size() < 3){
            throw new IllegalArgumentException("Sorry,but you must configure min 3 images");
        }
    }

    @Override
    public String firstImage() {
        return images.get(0);
    }

    @Override
    public String secondImage() {
        return images.get(1);
    }

    @Override
    public String thirdImage() {
        return images.get(2);
    }

    @Scheduled(cron = "${refresh-cron}")
    public void refresh(){
        LOGGER.info("Shuffle images");
       Collections.shuffle(images);
        System.out.println();
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Logger getLOGGER() {
        return LOGGER;
    }

    public void setLOGGER(Logger LOGGER) {
        this.LOGGER = LOGGER;
    }
}
