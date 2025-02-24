package com.pfeApp.jira.services;

import com.pfeApp.jira.Dto.JwtAuthenticationResponse;
import com.pfeApp.jira.Dto.RefreshTokenRequest;
import com.pfeApp.jira.Dto.SigninRequest;
import com.pfeApp.jira.Dto.SignupRequest;
import com.pfeApp.jira.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface AuthenticationService {
    User signup(SignupRequest signupRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
