package com.fiec.alunofiec.business.repositories;

import com.fiec.alunofiec.business.models.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMateriaRepositorio extends JpaRepository<Materia, String> {
}
