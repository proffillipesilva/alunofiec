package com.fiec.alunofiec.controllers;

import com.fiec.alunofiec.business.models.Aluno;
import com.fiec.alunofiec.services.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    IAlunoService alunoService;

    @GetMapping("/exemplo")  //  GET /alunos/exemplo
    public Aluno alunoExemplo(){
        return alunoService.alunoExemplo();
    }

    @GetMapping
    public List<Aluno> getAlunos(){
        return alunoService.getAlunos();
    }


}
