package com.fiec.alunofiec.services;

import com.fiec.alunofiec.business.models.dto.CreateAlunoRequest;
import com.fiec.alunofiec.business.models.dto.TokenResponse;
import com.fiec.alunofiec.business.models.entities.Aluno;
import com.fiec.alunofiec.business.models.entities.User;
import com.fiec.alunofiec.business.repositories.IAlunoRepositorio;
import com.fiec.alunofiec.business.repositories.IUserRepositorio;
import com.fiec.alunofiec.utils.JwtTokenUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class AlunoService implements IAlunoService{

    @Autowired
    IAlunoRepositorio alunoRepositorio;

    @Override
    public Page<Aluno> getAlunos(int page, int pageSize) {

        Page<Aluno> alunos =  alunoRepositorio.findAll(PageRequest.of(page, pageSize));
        return alunos;
    }

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    IUserRepositorio userRepositorio;

    @Override
    public void saveAluno(CreateAlunoRequest createAlunoRequest) throws GeneralSecurityException, IOException, HttpException {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        String clientId = "277380091468-1pe2je91eas7almtof0bf0bfhmehbvgi.apps.googleusercontent.com";

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
        GoogleIdToken idToken = verifier.verify(createAlunoRequest.getGoogleToken());
        if(idToken != null){
            GoogleIdToken.Payload payload = idToken.getPayload();

            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            String email = payload.getEmail();

            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email);
            if(userDetails != null){
                throw new HttpException();
            }
            User user = new User();
            user.setNome(createAlunoRequest.getNome());
            user.setEmail(email);
            User savedUser = userRepositorio.save(user);
            Aluno aluno = new Aluno();
            aluno.setUser(savedUser);
            aluno.setCurso(createAlunoRequest.getCurso());
            aluno.setRm(createAlunoRequest.getRm());
            alunoRepositorio.save(aluno);
        }
    }

    @Override
    public void atualizaAluno(Aluno aluno, String id) {

        Aluno alunoAnterior = alunoRepositorio.findById(id).orElse(null);
        alunoAnterior.setRm(aluno.getRm());
        alunoAnterior.setCurso(aluno.getCurso());
        alunoRepositorio.save(alunoAnterior);

    }

    @Override
    public Aluno pegaAluno(String id) {
        Aluno aluno = alunoRepositorio.findById(id).orElse(null);
        return aluno;
    }

    @Override
    public void deletaAluno(String id) {
        alunoRepositorio.deleteById(id);
    }
}
