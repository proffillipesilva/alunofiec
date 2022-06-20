package com.fiec.alunofiec.business.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_materias")
public class Materia {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ToString.Exclude
    private String materiaId;

    private String nome;

    @ManyToMany
    @JoinTable(name = "tb_cursa", joinColumns = {
            @JoinColumn(name = "materia_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "aluno_id")
    })
    List<Aluno> alunos;
}
