package workshop.music_workshop.model.view;

import javax.persistence.Column;

public class ArtistViewModel {

    private String name;
    private String careerInformation;

    public ArtistViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public void setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
    }
}
