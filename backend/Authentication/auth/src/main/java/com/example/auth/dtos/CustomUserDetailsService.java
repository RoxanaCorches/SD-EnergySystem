package com.example.auth.dtos;

import com.example.auth.entities.Auth;
import com.example.auth.repositories.AuthRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    public CustomUserDetailsService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Auth user = authRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("This user does not exist"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
    
    // Metodă pentru a obține obiectul Auth complet
    public Auth loadAuthUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}