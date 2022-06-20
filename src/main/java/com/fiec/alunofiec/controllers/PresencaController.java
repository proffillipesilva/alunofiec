package com.fiec.alunofiec.controllers;

import com.fiec.alunofiec.business.models.dto.PresencaRequest;
import com.fiec.alunofiec.business.models.dto.PresencaResponse;
import com.fiec.alunofiec.business.models.entities.Aluno;
import com.fiec.alunofiec.business.models.entities.Materia;
import com.fiec.alunofiec.business.models.entities.Presenca;
import com.fiec.alunofiec.business.models.entities.User;
import com.fiec.alunofiec.business.repositories.IAlunoRepositorio;
import com.fiec.alunofiec.business.repositories.IMateriaRepositorio;
import com.fiec.alunofiec.business.repositories.IPresencaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/presencas")
public class PresencaController {

    @Autowired
    IAlunoRepositorio alunoRepositorio;

    @Autowired
    IMateriaRepositorio materiaRepositorio;

    @Autowired
    IPresencaRepositorio presencaRepositorio;

    @PostMapping
    public void criaPresenca(PresencaRequest request, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Aluno aluno = alunoRepositorio.findByUser(user).orElseThrow();
        Materia materia = materiaRepositorio.findById(request.getMateriaId()).orElseThrow();

        Presenca presenca = Presenca.builder()
                .aluno(aluno)
                .materia(materia)
                .lat(request.getLat())
                .lng(request.getLng())
                .numAula(request.getNumAula())
                .build();
        presencaRepositorio.save(presenca);

    }

    @GetMapping
    public PresencaResponse pegaPresenca(){
        return PresencaResponse.builder()
                .materiaId("456465446546546")
                .numAula(2)
                .build();

    }

}
