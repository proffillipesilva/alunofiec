package com.fiec.alunofiec.business.repositories;

import com.fiec.alunofiec.business.models.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAlunoRepositorio extends JpaRepository<Aluno, String> {

    List<Aluno> findByNomeAndCurso(String nome, String curso);
}
