package workshop.music_workshop.services;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarouselServiceImplTest {

    private CarouselServiceImpl serviceToTest;

    @BeforeEach
    public void setUp() {
        serviceToTest = new CarouselServiceImpl(
                List.of("A", "B", "C"),
                new TestImageShuffler()
        );
    }

    @Test
    public void testRefresh() {
        Assertions.assertEquals("A", serviceToTest.firstImage());
        List<String> images = serviceToTest.getImages();
        Collections.reverse(images);
        serviceToTest.setImages(serviceToTest.getImages());
        Assertions.assertEquals("C", serviceToTest.firstImage());
        Assertions.assertEquals("B", serviceToTest.secondImage());
        Assertions.assertEquals("A", serviceToTest.thirdImage());
        serviceToTest.refresh();
    }

   public static class TestImageShuffler implements ImageShuffler {
        @Override
        public void shuffle(List<String> images) {
            Collections.reverse(images);
        }
    }

}