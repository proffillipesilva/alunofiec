package com.fiec.alunofiec.controllers;

import com.fiec.alunofiec.business.models.dto.FirebaseAuthRequest;
import com.fiec.alunofiec.business.models.dto.TokenResponse;
import com.fiec.alunofiec.business.models.entities.User;
import com.fiec.alunofiec.business.repositories.IUserRepositorio;
import com.fiec.alunofiec.services.IFirebaseService;
import com.fiec.alunofiec.services.JwtUserDetailsService;
import com.fiec.alunofiec.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
