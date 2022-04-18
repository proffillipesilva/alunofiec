package com.fiec.alunofiec.business.repositories;

import com.fiec.alunofiec.business.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepositorio extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
