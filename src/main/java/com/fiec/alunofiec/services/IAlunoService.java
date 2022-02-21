package com.fiec.alunofiec.services;


import com.fiec.alunofiec.models.Aluno;

import java.util.List;

public interface IAlunoService {

    Aluno alunoExemplo();

    List<Aluno> getAlunos();
}
