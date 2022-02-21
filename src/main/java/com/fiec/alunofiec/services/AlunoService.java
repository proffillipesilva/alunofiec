package com.fiec.alunofiec.services;

import com.fiec.alunofiec.models.Aluno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService implements IAlunoService{
    @Override
    public Aluno alunoExemplo() {
        return new Aluno(1, "Joao", "TI",
                "https://st.depositphotos.com/1743476/3119/i/600/depositphotos_31199439-stock-photo-happy-student-thumb-up.jpg");
    }

    @Override
    public List<Aluno> getAlunos() {
        Aluno aluno1 = new Aluno(1, "Joao", "TI",
                "https://st.depositphotos.com/1743476/3119/i/600/depositphotos_31199439-stock-photo-happy-student-thumb-up.jpg");
        Aluno aluno2= new Aluno(2, "Maria", "TI",
                "https://images.unsplash.com/photo-1544717305-2782549b5136?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c3R1ZGVudHxlbnwwfHwwfHw%3D&w=1000&q=80");

        return List.of(aluno1, aluno2);
    }
}
