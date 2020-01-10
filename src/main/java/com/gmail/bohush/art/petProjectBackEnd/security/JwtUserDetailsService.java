package com.gmail.bohush.art.petProjectBackEnd.security;

import com.gmail.bohush.art.petProjectBackEnd.entity.User;
import com.gmail.bohush.art.petProjectBackEnd.security.jwt.JwtUser;
import com.gmail.bohush.art.petProjectBackEnd.security.jwt.JwtUserFactory;
import com.gmail.bohush.art.petProjectBackEnd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + email + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", email);
        return jwtUser;
    }
}
