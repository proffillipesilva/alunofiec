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

    @GetMapping
    public List<Aluno> getAlunos(){
        return alunoService.getAlunos();
    }

    @PostMapping
    public void saveAluno(@RequestBody Aluno aluno){
        alunoService.saveAluno(aluno);
    }

    @GetMapping("/{alunoId}")
    public Aluno pegaAluno(@PathVariable("alunoId") String id){

        return alunoService.pegaAluno(id);
    }

    @PutMapping("/{alunoId}")
    public void atualizaAluno(@PathVariable("alunoId") String id, @RequestBody Aluno aluno){
        alunoService.atualizaAluno(aluno, id);
    }

    @DeleteMapping("/{alunoId}")
    public void deletaAluno(@PathVariable("alunoId") String id){
        alunoService.deletaAluno(id);
    }




}
