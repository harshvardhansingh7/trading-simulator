package com.trading.simulator.service;

import com.trading.simulator.entity.User;
import com.trading.simulator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Return Spring Security user
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
                Collections.singleton(() -> u.getRoles() == null ? "ROLE_USER" : u.getRoles())
        );
    }
}
