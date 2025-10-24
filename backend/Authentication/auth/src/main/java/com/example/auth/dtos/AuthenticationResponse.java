package com.example.auth.dtos;

public class AuthenticationResponse {

    private String token;
    private AuthDTO authUserDTO;

    public AuthenticationResponse() {}

    public AuthenticationResponse(String token, AuthDTO authUserDTO) {
        this.token = token;
        this.authUserDTO = authUserDTO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthDTO getAuthUserDTO() {
        return authUserDTO;
    }

    public void setAuthUserDTO(AuthDTO authUserDTO) {
        this.authUserDTO = authUserDTO;
    }
}
