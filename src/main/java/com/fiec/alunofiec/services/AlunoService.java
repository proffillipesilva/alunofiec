package com.fiec.alunofiec.services;

import com.fiec.alunofiec.business.models.Aluno;
import com.fiec.alunofiec.business.repositories.IAlunoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService implements IAlunoService{

    @Autowired
    IAlunoRepositorio alunoRepositorio;

    @Override
    public List<Aluno> getAlunos() {

        List<Aluno> alunos = new ArrayList<>();
        alunoRepositorio.findAll().forEach(alunos::add);
        return alunos;

    }

    @Override
    public void saveAluno(Aluno aluno) {
        alunoRepositorio.save(aluno);
    }

    @Override
    public void atualizaAluno(Aluno aluno, String id) {

        Aluno alunoAnterior = alunoRepositorio.findById(id).orElse(null);
        alunoAnterior.setRm(aluno.getRm());
        alunoAnterior.setNome(aluno.getNome());
        alunoAnterior.setCurso(aluno.getCurso());
        alunoAnterior.setProfileImage(aluno.getProfileImage());
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
