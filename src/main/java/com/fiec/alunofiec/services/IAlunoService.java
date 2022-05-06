package com.fiec.alunofiec.services;


import com.fiec.alunofiec.business.models.entities.Aluno;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAlunoService {

    Page<Aluno> getAlunos(int page, int pageSize);

    void saveAluno(Aluno aluno);

    void atualizaAluno(Aluno aluno, String id);

    Aluno pegaAluno(String id);

    void deletaAluno(String id);
}
