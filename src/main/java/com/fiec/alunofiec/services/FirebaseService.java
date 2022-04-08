package com.fiec.alunofiec.services;

import com.fiec.alunofiec.business.models.dto.FirebaseAuthRequest;
import com.fiec.alunofiec.business.models.dto.FirebaseAuthResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FirebaseService implements IFirebaseService{
    private String WEBKEY = "ColoqueSeuWebKey";
    private String loginUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword" +
            "?key=" +  WEBKEY;
    private String registerUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signUp" +
            "?key=" + WEBKEY ;
    @Override
    public void register(FirebaseAuthRequest firebaseAuthRequest) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FirebaseAuthRequest> httpEntity = new HttpEntity<>(firebaseAuthRequest);
        System.out.println(registerUrl);
        ResponseEntity<FirebaseAuthResponse> response =
                restTemplate.exchange(registerUrl, HttpMethod.POST, httpEntity, FirebaseAuthResponse.class);

        if(response.getStatusCodeValue() != 200 ){
            throw new Exception("Nao pode registrar");
        }
        System.out.println(response.getBody());
    }

    @Override
    public void login(FirebaseAuthRequest firebaseAuthRequest) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FirebaseAuthRequest> httpEntity = new HttpEntity<>(firebaseAuthRequest);
        ResponseEntity<FirebaseAuthResponse> response =
                restTemplate.exchange(loginUrl, HttpMethod.POST, httpEntity, FirebaseAuthResponse.class);
        if(response.getStatusCodeValue() != 200  ){
            throw new Exception("Nao pode logar");
        }
        System.out.println(response.getBody());
        UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(response.getBody().getEmail(), response.getBody().getIdToken());
        SecurityContextHolder.getContext().setAuthentication(t);
    }
}
