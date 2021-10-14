package workshop.music_workshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import workshop.music_workshop.model.entities.UserEntity;
import workshop.music_workshop.model.entities.UserRole;
import workshop.music_workshop.model.entities.UserRoleEntity;
import workshop.music_workshop.model.services.UserRegistrationServiceModel;
import workshop.music_workshop.model.services.UserServiceModel;
import workshop.music_workshop.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final ModelMapper mapper;
    private final MusicDBUserService musicDbUserService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleService userRoleService, ModelMapper mapper, MusicDBUserService musicDbUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
        this.mapper = mapper;
        this.musicDbUserService = musicDbUserService;
    }

    @Override
    public void seedUserEntity() {
        if(userRepository.count() == 0){
        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setFullname("Admin Adminov");
        admin.setPassword(this.passwordEncoder.encode("12345"));
        admin.setRoles(this.userRoleService.findAll());
        this.userRepository.save(admin);

        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setFullname("User Userov");
        user.setPassword(this.passwordEncoder.encode("12345"));
        user.setRoles(this.userRoleService.findAllByUserRole(UserRole.USER));
        this.userRepository.save(user);
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserEntity userEntity = this.mapper.map(userRegistrationServiceModel, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        UserRoleEntity userRoleEntity = this.userRoleService.findByUserRole(UserRole.USER);
        userEntity.addRole(userRoleEntity);
             this.userRepository.save(userEntity);
        UserDetails principal = this.musicDbUserService.loadUserByUsername(userEntity.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal, userEntity.getPassword(), principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean userIsExists(String username) {
        UserEntity userEntity = this.userRepository.findByUsername(username);
        if(userEntity == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public UserEntity findByUsername(String name) {
        return this.userRepository.findByUsername(name);
    }

    @Override
    public UserServiceModel findById(Long id) {
        return this.mapper.map(this.userRepository.findById(id), UserServiceModel.class);
    }

}
