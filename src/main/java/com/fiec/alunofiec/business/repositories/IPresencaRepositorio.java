package com.fiec.alunofiec.business.repositories;

import com.fiec.alunofiec.business.models.entities.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPresencaRepositorio extends JpaRepository<Presenca, Integer> {

}
