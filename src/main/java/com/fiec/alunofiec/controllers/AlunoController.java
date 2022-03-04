package com.fiec.alunofiec.controllers;

import com.fiec.alunofiec.business.models.Aluno;
import com.fiec.alunofiec.services.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void saveAluno(@RequestBody Aluno aluno){
        alunoService.saveAluno(aluno);
    }


}
