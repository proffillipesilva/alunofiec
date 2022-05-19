package com.fiec.alunofiec.services;

import com.fiec.alunofiec.business.models.dto.CreateAlunoRequest;
import com.fiec.alunofiec.business.models.entities.Aluno;
import com.fiec.alunofiec.business.models.entities.Materia;
import com.fiec.alunofiec.business.models.entities.User;
import com.fiec.alunofiec.business.repositories.IAlunoRepositorio;
import com.fiec.alunofiec.business.repositories.IMateriaRepositorio;
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

@Service
public class MateriaService implements IMateriaService{

    @Autowired
    IMateriaRepositorio materiaRepositorio;

    @Override
    public Page<Materia> getMaterias(int page, int pageSize) {
        return materiaRepositorio.findAll(PageRequest.of(page,pageSize));
    }

    @Override
    public void saveAluno(Materia materia) {
        materiaRepositorio.save(materia);
    }

    @Override
    public void atualizaMateria(Materia materia, String id) {
        Materia materiaAnterior = materiaRepositorio.findById(id).orElse(null);
        materiaAnterior.setMateriaNome(materia.getMateriaNome());
        materiaAnterior.setCurso(materia.getCurso());
        materiaRepositorio.save(materiaAnterior);
    }

    @Override
    public Materia pegaMateria(String id) {
        return materiaRepositorio.findById(id).orElseThrow();
    }

    @Override
    public void deletaMateria(String id) {
        materiaRepositorio.deleteById(id);
    }
}
