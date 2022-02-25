package com.fiec.alunofiec.business.repositories;

import com.fiec.alunofiec.business.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IAlunoRepositorio extends JpaRepository<Aluno, UUID> {

    List<Aluno> findByNomeAndCurso(String nome, String curso);
}
