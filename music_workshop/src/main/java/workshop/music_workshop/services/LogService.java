package workshop.music_workshop.services;

import workshop.music_workshop.model.services.LogServiceModel;

import java.util.List;

public interface LogService {
    void createLog(String action, Long albumId);

    List<LogServiceModel> findAllLogs();
}
