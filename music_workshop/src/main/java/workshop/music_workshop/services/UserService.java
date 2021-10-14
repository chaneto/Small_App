package workshop.music_workshop.services;


import workshop.music_workshop.model.entities.UserEntity;
import workshop.music_workshop.model.services.UserRegistrationServiceModel;
import workshop.music_workshop.model.services.UserServiceModel;

public interface UserService {
 void seedUserEntity();

 void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

   boolean userIsExists(String username);

    UserEntity findByUsername(String name);

    UserServiceModel findById(Long id);
}
