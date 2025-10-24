package com.example.auth.services;

import com.example.auth.config.JwtService;
import com.example.auth.dtos.*;
import com.example.auth.entities.Auth;
import com.example.auth.entities.Role;
import com.example.auth.repositories.AuthRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Autowired
    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, RestTemplate restTemplate) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.restTemplate = restTemplate;
    }


    /*
    public AuthenticationResponse register(RegisterRequest request) {
        // Creăm noul utilizator
        Auth user = new Auth();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CLIENT); // rol implicit (poți schimba în CLIENT dacă ai acel enum)

        // Salvăm în baza de date
        Auth savedUser = authRepository.save(user);

        // Generăm token JWT
        String token = jwtService.generateToken(savedUser);

        // Creăm obiectul DTO pentru răspuns
        AuthDTO userDTO = new AuthDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                null, // email (dacă nu e stocat în Auth)
                savedUser.getRole().name()
        );

        // Returnăm răspunsul
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        response.setAuthUserDTO(userDTO);
        return response;
    }
     */

/*
    public AuthenticationResponse register(RegisterRequest request) {
        // Creăm noul utilizator
        Auth user = new Auth();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CLIENT); // rol implicit (poți schimba în CLIENT dacă ai acel enum)

        // Salvăm în baza de date
        Auth savedUser = authRepository.save(user);

        // Generăm token JWT
        String token = jwtService.generateToken(savedUser);

        // Creăm obiectul DTO pentru răspuns
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(
                savedUser.getId(),
                request.getName(),
                savedUser.getUsername(),
                null, // parola nu trebuie să fie trimisă necriptată
                request.getEmail(),
                request.getAddress(),
                request.getAge(),
                savedUser.getRole().name()
        );

        String url = userServiceUrl + "/register";
        try {
            org.springframework.http.HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UserDetailsDTO> requestEntity = new HttpEntity<>(userDetailsDTO, headers);

            restTemplate.postForEntity(url, requestEntity, Void.class);
        } catch (Exception e) {
            // Dacă nu reușește să trimită datele la UserService, șterge utilizatorul Auth și aruncă eroare
            authRepository.delete(savedUser);
            throw new RuntimeException("Nu s-a putut crea utilizatorul în UserService", e);
        }
        // Returnăm răspunsul
        AuthDTO authDTO = new AuthDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                null,
                savedUser.getRole().name()
        );

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        response.setAuthUserDTO(authDTO);

        return response;
    }

 */

    public AuthenticationResponse register(RegisterRequest request, String adminToken) {

        Auth user = new Auth();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CLIENT);

        Auth savedUser = authRepository.save(user);


        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(
                savedUser.getId(),
                request.getName(),
                savedUser.getUsername(),
                savedUser.getPassword(),
                request.getEmail(),
                request.getAddress(),
                request.getAge(),
                savedUser.getRole().name()
        );

        String url = userServiceUrl + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(adminToken);

        HttpEntity<UserDetailsDTO> requestEntity = new HttpEntity<>(userDetailsDTO, headers);

        ResponseEntity<UserDetailsDTO> response = restTemplate.postForEntity(url, requestEntity, UserDetailsDTO.class);
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Body: " + response.getBody());
        AuthDTO authDTO = new AuthDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getPassword()
        );

        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setToken(jwtService.generateToken(savedUser));
        authResponse.setAuthUserDTO(authDTO);

        return authResponse;
    }




    // ===========================
    // LOGIN
    // ===========================
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Auth user = authRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        AuthDTO userDTO = new AuthDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        response.setAuthUserDTO(userDTO);
        return response;
    }





    // ===========================
    // HELPER - TEST HASH
    // ===========================

    @PostConstruct
    public void generateHash() {
        String hash = passwordEncoder.encode("admin123");
        System.out.println("🔑 New bcrypt hash for 'admin123': " + hash);
    }

    @PostConstruct
    public void testPassword() {
        String rawPassword = "admin123";
        String hashFromDb = " $2a$10$yZSLtop2cLSiGnfPOSspmezk4rkQXN63wSz19DHqtDtCjmnMpGHGC"; // hash real
        boolean matches = passwordEncoder.matches(rawPassword, hashFromDb);
        System.out.println("✅ BCrypt match test: " + matches);
    }

}
