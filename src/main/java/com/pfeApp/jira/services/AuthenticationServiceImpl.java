package com.pfeApp.jira.services;

import com.pfeApp.jira.Dto.JwtAuthenticationResponse;
import com.pfeApp.jira.Dto.RefreshTokenRequest;
import com.pfeApp.jira.Dto.SigninRequest;
import com.pfeApp.jira.Dto.SignupRequest;
import com.pfeApp.jira.entities.Role;
import com.pfeApp.jira.entities.User;
import com.pfeApp.jira.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.lang.*;
import java.util.HashMap;

import static java.lang.invoke.MethodHandles.throwException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepo ur ;
    private final PasswordEncoder passwordEncoder ;
    private final AuthenticationManager authenticationManager ;
    private final JWTService jwtService ;
    public User signup(SignupRequest signupRequest){
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstname(signupRequest.getFirstName());
        user.setLastname(signupRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        return ur.save(user);//Méthode pour inscrire un nouvel utilisateur. Le mot de passe est encodé avant d'être sauvegardé.
    }
    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail() , signinRequest.getPassword()));

        var user = ur.findByEmail(signinRequest.getEmail());
        if (user == null) throw new IllegalArgumentException("Invalid ");/*.orElseThrow(() -> new IllegalArgumentException("Invalid "));*/
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>() , user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse ;// Méthode pour connecter un utilisateur. Elle génère un JWT et un refresh token.
}
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = ur.findByEmail(userEmail);
        if (user == null) throw new IllegalArgumentException("Invalid ");
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse ;
        }
        return null ;
    }
}

