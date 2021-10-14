package workshop.music_workshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workshop.music_workshop.model.entities.UserRole;
import workshop.music_workshop.model.entities.UserRoleEntity;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findAll();
    List<UserRoleEntity> findAllByUserRole(UserRole userRole);
    UserRoleEntity findByUserRole(UserRole userRole);
}
