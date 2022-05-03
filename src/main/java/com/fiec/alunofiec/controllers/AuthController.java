package com.fiec.alunofiec.controllers;

import com.fiec.alunofiec.business.models.dto.FirebaseAuthRequest;
import com.fiec.alunofiec.business.models.dto.GoogleAuthRequest;
import com.fiec.alunofiec.business.models.dto.TokenResponse;
import com.fiec.alunofiec.business.models.entities.User;
import com.fiec.alunofiec.business.repositories.IUserRepositorio;
import com.fiec.alunofiec.services.IFirebaseService;
import com.fiec.alunofiec.services.JwtUserDetailsService;
import com.fiec.alunofiec.utils.JwtTokenUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IFirebaseService firebaseService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    IUserRepositorio userRepositorio;

    @PostMapping("/register")
    public void registraUsuarioFirebase(@RequestBody FirebaseAuthRequest firebaseAuthRequest) throws Exception {
        firebaseService.register(firebaseAuthRequest);
        String email = firebaseAuthRequest.getEmail();
        User user = new User();
        user.setEmail(email);
        userRepositorio.save(user);
    }

    @PostMapping("/login")
    public TokenResponse autenticaUsuarioFirebase(@RequestBody FirebaseAuthRequest firebaseAuthRequest) throws Exception {
        firebaseService.login(firebaseAuthRequest);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(firebaseAuthRequest.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new TokenResponse(token);

    }

    @PostMapping("/loginWithGoogle")
    public TokenResponse autenticaUsuariocomGoogle(@RequestBody GoogleAuthRequest googleAuthRequest) throws Exception {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        String clientId = "277380091468-1pe2je91eas7almtof0bf0bfhmehbvgi.apps.googleusercontent.com";

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
        GoogleIdToken idToken = verifier.verify(googleAuthRequest.getGoogleToken());
        if(idToken != null){
            GoogleIdToken.Payload payload = idToken.getPayload();

            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");

            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);
            String token = jwtTokenUtil.generateToken(userDetails);
            return new TokenResponse(token);
        }
        throw new HttpException();

    }
}
