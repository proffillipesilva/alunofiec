package com.fiec.alunofiec.business.repositories;

import com.fiec.alunofiec.business.models.entities.Aluno;
import com.fiec.alunofiec.business.models.entities.Materia;
import com.fiec.alunofiec.business.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMateriaRepositorio extends JpaRepository<Materia, String> {

}
