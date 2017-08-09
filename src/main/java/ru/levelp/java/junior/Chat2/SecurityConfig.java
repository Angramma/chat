package ru.levelp.java.junior.Chat2;

//import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import java.net.Authenticator;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().and()
                .authorizeRequests()
                //.antMatchers("/signin", "/login", "/", "/styles/**", "/to_singup", "/singup").permitAll()
                .antMatchers("/chat").hasRole("USER")
                //.anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .failureForwardUrl("/index")
                .successForwardUrl("/chat")
                .loginPage("/signin")
                .loginProcessingUrl("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID");
    }
   /* @Autowired
    private Authenticator userDetails;
    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
                auth
                .userDetailsService(userDetails)
                .passwordEncoder(passwordEncoder());
    }*/

    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder encoder, Authenticator authenticator) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(authenticator);

        return provider;
    }
}
