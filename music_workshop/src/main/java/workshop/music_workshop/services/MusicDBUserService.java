package workshop.music_workshop.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import workshop.music_workshop.model.entities.UserEntity;
import workshop.music_workshop.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MusicDBUserService implements UserDetailsService {

    private final UserRepository userRepository;

    public MusicDBUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(name);
        if(userEntity == null){
            throw new UsernameNotFoundException("User with" + name + " was not found!!!");
        }
        return mapToUserDetail(userEntity);
    }

    private UserDetails mapToUserDetail(UserEntity userEntity){
        List<GrantedAuthority> authorities = userEntity
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getUserRole().name())).collect(Collectors.toList());
        UserDetails result = new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
        return result;
    }
}
