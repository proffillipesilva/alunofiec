package com.fiec.alunofiec.services;


import com.fiec.alunofiec.business.models.Aluno;

import java.util.List;

public interface IAlunoService {

    Aluno alunoExemplo();

    List<Aluno> getAlunos();
}
