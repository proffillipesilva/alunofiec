package com.fiec.alunofiec.services;

import com.fiec.alunofiec.business.models.Aluno;
import com.fiec.alunofiec.business.repositories.IAlunoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService implements IAlunoService{

    @Autowired
    IAlunoRepositorio alunoRepositorio;

    @Override
    public Aluno alunoExemplo() {
        //return new Aluno(1, "Joao", "TI",
                //"https://st.depositphotos.com/1743476/3119/i/600/depositphotos_31199439-stock-photo-happy-student-thumb-up.jpg");
        return null;
    }

    @Override
    public List<Aluno> getAlunos() {

        return alunoRepositorio.findAll();

    }
}
