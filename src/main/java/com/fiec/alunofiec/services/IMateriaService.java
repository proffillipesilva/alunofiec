package com.fiec.alunofiec.services;


import com.fiec.alunofiec.business.models.entities.Materia;
import org.springframework.data.domain.Page;

public interface IMateriaService {

    Page<Materia> getMaterias(int page, int pageSize);

    void saveAluno(Materia materia);

    void atualizaMateria(Materia materia, String id);

    Materia pegaMateria(String id);

    void deletaMateria(String id);
}
