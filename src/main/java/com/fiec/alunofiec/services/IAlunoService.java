package com.fiec.alunofiec.services;


import com.fiec.alunofiec.business.models.entities.Aluno;

import java.util.List;

public interface IAlunoService {

    List<Aluno> getAlunos();

    void saveAluno(Aluno aluno);

    void atualizaAluno(Aluno aluno, String id);

    Aluno pegaAluno(String id);

    void deletaAluno(String id);
}
