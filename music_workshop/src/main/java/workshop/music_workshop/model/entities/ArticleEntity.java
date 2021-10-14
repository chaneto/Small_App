package workshop.music_workshop.model.entities;


import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import workshop.music_workshop.model.entities.Genre;

@Entity
@Table(name="articles")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDate createdOn;
    @ManyToOne
    private UserEntity userEntity;

    public String getTitle() {
        return title;
    }

    public ArticleEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArticleEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public ArticleEntity setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public ArticleEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public ArticleEntity setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}

