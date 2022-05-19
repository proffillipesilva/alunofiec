package com.fiec.alunofiec.business.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_aulas")
public class Aula {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ToString.Exclude
    private String aulaId;

    private int numAula;

    private String diaAula;

    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    @ManyToMany
    @JoinTable(name = "tb_status", joinColumns = {@JoinColumn(name = "aula_id")}, inverseJoinColumns = {@JoinColumn(name = "aluno_id")} )
    private List<Aluno> alunos = new ArrayList<>();

    private String conteudo;
}
