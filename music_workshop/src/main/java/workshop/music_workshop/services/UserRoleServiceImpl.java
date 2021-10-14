package workshop.music_workshop.services;

import org.springframework.stereotype.Service;
import workshop.music_workshop.model.entities.UserRole;
import workshop.music_workshop.model.entities.UserRoleEntity;
import workshop.music_workshop.repositories.UserRoleRepository;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void seedUsersRoleEntity() {
        if(this.userRoleRepository.count() == 0){

            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setUserRole(UserRole.ADMIN);
            this.userRoleRepository.save(adminRole);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setUserRole(UserRole.USER);
            this.userRoleRepository.save(userRole);
        }
    }

    @Override
    public List<UserRoleEntity> findAll() {
        return this.userRoleRepository.findAll();
    }

    @Override
    public List<UserRoleEntity> findAllByUserRole(UserRole userRole) {
        return this.userRoleRepository.findAllByUserRole(userRole);
    }

    @Override
    public UserRoleEntity findByUserRole(UserRole userRole) {
        return this.userRoleRepository.findByUserRole(userRole);
    }
}
