package workshop.music_workshop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import workshop.music_workshop.services.MusicDBUserService;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MusicDBUserService musicDbUserService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(MusicDBUserService musicDbUserService, PasswordEncoder passwordEncoder) {
        this.musicDbUserService = musicDbUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               //allow access to static resources to anyone
               //позволяване на достъп до статични ресурси на всеки
               .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
               //allow access to index user login and registration to anyone
               //позволява достъп до страниците за влизане и регистрация на всеки потребител
               .antMatchers("/", "/users/login", "/users/register").permitAll()
               .antMatchers("/articles/add").hasRole("ADMIN")
               //protect all other pages
               //защита на всички други страници
               .antMatchers("/**")
               .authenticated()
       .and()
               //configure login with HTML
       .formLogin()
               //our login page will be served by the controller with mapping /users/login
               //нашата страница за вход ще се обслужва от контролера с картографиране /потребители /вход
       .loginPage("/users/login")
               //the name of the user name input field in OUR login form is username (optional)
               //името на полето за въвеждане на потребителско име във нашата форма за вход е username (по избор)
       .usernameParameter("username")
               //замества се с : UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY
               //the name of the user password input field in OUR login form is password (optional)
               //името на полето за въвеждане на потребителска парола във НАШАТА форма за вход е password (по избор)
       .passwordParameter("password")
               //замества се с : UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY
               //on login success redirect here
               //при успешното влизане пренасочване тук
       .defaultSuccessUrl("/home")
               //on login failure redirect here
               //при неуспешно влизане пренасочване тук
      .failureForwardUrl("/users/login-error")
       .and()
       .logout()
               .logoutUrl("/logout")
               .logoutSuccessUrl("/")
               .invalidateHttpSession(true)
               .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.musicDbUserService)
        .passwordEncoder(this.passwordEncoder);
    }
}
