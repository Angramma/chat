package ru.levelp.java.junior.Chat2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Authenticator implements UserDetailsService {
    private final ChatFacadeDAO dao;

    @Autowired
    public Authenticator(ChatFacadeDAO dao) {
        this.dao = dao;
    }

    @Autowired
    public PasswordEncoder encoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.levelp.java.junior.Chat2.User user = dao.findUserByLogin(username);
        if (user == null) throw new UsernameNotFoundException("No user " + username);

        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();

        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        User userRes = new User(username, encoder.encode(user.getPassword()), roles);
        return userRes;
    }
}
