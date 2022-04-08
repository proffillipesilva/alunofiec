package com.fiec.alunofiec.controllers;

import com.fiec.alunofiec.business.models.dto.FirebaseAuthRequest;
import com.fiec.alunofiec.services.IFirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    IFirebaseService firebaseService;

    @PostMapping("/register")
    public void registraUsuarioFirebase(@RequestBody FirebaseAuthRequest firebaseAuthRequest) throws Exception {
        firebaseService.register(firebaseAuthRequest);
    }

    @PostMapping("/login")
    public void autenticaUsuarioFirebase(@RequestBody FirebaseAuthRequest firebaseAuthRequest) throws Exception {
        firebaseService.login(firebaseAuthRequest);
    }
}
