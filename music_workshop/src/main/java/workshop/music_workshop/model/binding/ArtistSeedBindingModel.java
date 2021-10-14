package workshop.music_workshop.model.binding;

import com.google.gson.annotations.Expose;

public class ArtistSeedBindingModel {

    @Expose
    private String name;

    @Expose
    private String careerInformation;

    public ArtistSeedBindingModel() {
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
