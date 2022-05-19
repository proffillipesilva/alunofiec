package com.fiec.alunofiec.services;


import com.fiec.alunofiec.business.models.entities.Aluno;
import com.fiec.alunofiec.business.models.entities.Aula;
import org.springframework.data.domain.Page;

public interface IAulaService {

    Page<Aula> getAulas(int page, int pageSize);

    void saveAula(Aula aula);

    void atualizaAula(Aula aula, String id);

    Aula pegaAula(String id);

    void deletaAula(String id);
}
