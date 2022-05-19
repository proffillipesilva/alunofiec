package com.fiec.alunofiec.services;


import com.fiec.alunofiec.business.models.dto.CreateAlunoRequest;
import com.fiec.alunofiec.business.models.entities.Aluno;
import org.apache.http.HttpException;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface IAlunoService {

    Page<Aluno> getAlunos(int page, int pageSize);

    void saveAluno(CreateAlunoRequest createAlunoRequest) throws GeneralSecurityException, IOException, HttpException;

    void atualizaAluno(Aluno aluno, String id);

    Aluno pegaAluno(String id);

    void deletaAluno(String id);
}
