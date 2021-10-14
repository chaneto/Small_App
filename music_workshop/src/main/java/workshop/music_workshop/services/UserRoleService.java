package workshop.music_workshop.services;

import workshop.music_workshop.model.entities.UserRole;
import workshop.music_workshop.model.entities.UserRoleEntity;

import java.util.List;

public interface UserRoleService {

    void seedUsersRoleEntity();

    List<UserRoleEntity> findAll();
    List<UserRoleEntity> findAllByUserRole(UserRole userRole);
    UserRoleEntity findByUserRole(UserRole userRole);
}
