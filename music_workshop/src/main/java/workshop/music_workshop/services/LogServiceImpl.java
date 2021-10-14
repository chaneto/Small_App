package workshop.music_workshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import workshop.music_workshop.model.entities.AlbumEntity;
import workshop.music_workshop.model.entities.LogEntity;
import workshop.music_workshop.model.entities.UserEntity;
import workshop.music_workshop.model.services.LogServiceModel;
import workshop.music_workshop.repositories.LogRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final AlbumService albumService;
    private final UserService userService;
    private final ModelMapper mapper;

    public LogServiceImpl(LogRepository logRepository, AlbumService albumService, UserService userService, ModelMapper mapper) {
        this.logRepository = logRepository;
        this.albumService = albumService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public void createLog(String action, Long albumId) {
        AlbumEntity albumEntity = this.albumService.findById(albumId).orElse(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = this.userService.findByUsername(username);
        LogEntity logEntity = new LogEntity();
        logEntity.setAlbumEntity(albumEntity);
        logEntity.setUserEntity(userEntity);
        logEntity.setAction(action);
        logEntity.setLocalDateTime(LocalDateTime.now());

        this.logRepository.save(logEntity);
    }

    @Override
    public List<LogServiceModel> findAllLogs() {
        List<LogEntity> entities = this.logRepository.findAll();
        List<LogServiceModel> logServiceModels = new ArrayList<>();
        for(LogEntity out: entities){
            LogServiceModel logServiceModel = this.mapper.map(out, LogServiceModel.class);
            logServiceModel.setAlbumEntity(out.getAlbumEntity().getName());
            logServiceModel.setUserEntity(out.getUserEntity().getUsername());
           logServiceModels.add(logServiceModel);
        }
        return logServiceModels;
    }
}
