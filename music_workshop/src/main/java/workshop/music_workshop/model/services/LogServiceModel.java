package workshop.music_workshop.model.services;

import workshop.music_workshop.model.entities.AlbumEntity;
import workshop.music_workshop.model.entities.UserEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class LogServiceModel {

    private Long id;
    private String userEntity;
    private String albumEntity;
    private String action;
    private LocalDateTime localDateTime;

    public LogServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(String userEntity) {
        this.userEntity = userEntity;
    }

    public String getAlbumEntity() {
        return albumEntity;
    }

    public void setAlbumEntity(String albumEntity) {
        this.albumEntity = albumEntity;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
