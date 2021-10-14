package workshop.music_workshop.model.binding;

import org.springframework.format.annotation.DateTimeFormat;
import workshop.music_workshop.model.entities.Genre;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumAddBindingModel {

    @NotBlank(message = "Name cannot be null or empty string!!!")
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!!!")
    private String name;
    @NotBlank(message = "URL cannot be null or empty string!!!")
    @Size(min = 5, message = "URL length must be minimum 5 characters!!!")
    private String imageUrl;
    private String videoUrl;
    @NotBlank(message = "Description cannot be null or empty string!!!")
    @Size(min = 5, message = "Description min length must be minimum 5!!!")
    private String description;
    @Min(value = 10, message = "Must be a more than 10!!!")
    @NotNull(message = "Copies cannot be null!!!")
    private Integer copies;
    @DecimalMin(value = "1", message = "Price must be a positive number!!!")
    private BigDecimal price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "The date cannot be in the future!")
    private LocalDate releaseDate;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Genre cannot be null!!!")
    private Genre genre;
    @NotBlank(message = "Artist cannot be null or empty string!!!")
    private String artist;

    public AlbumAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
