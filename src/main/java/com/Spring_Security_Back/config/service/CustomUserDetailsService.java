package com.Spring_Security_Back.config.service;

import com.Spring_Security_Back.config.models.UserDetailsImpl;
import com.Spring_Security_Back.user.interfaces.GetUserData;
import com.Spring_Security_Back.user.models.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final GetUserData getUserData;

    public CustomUserDetailsService(GetUserData getUserData) {
        this.getUserData = getUserData;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserData.getUserByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException("El usuario no se encuentra registrado");
        }

        return new UserDetailsImpl(user);
    }
}