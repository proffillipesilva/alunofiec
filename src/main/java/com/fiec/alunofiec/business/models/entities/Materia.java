package com.fiec.alunofiec.business.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_materias")
public class Materia {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ToString.Exclude
    private String materiaId;

    private String materiaNome;

    private String curso;

    @CreationTimestamp
    private Date createdTime;

    @UpdateTimestamp
    private Date updatedTime;

    @ManyToMany
    @JoinTable(name = "tb_cursa", joinColumns = {@JoinColumn(name = "materia_id")}, inverseJoinColumns = {@JoinColumn(name = "aluno_id")} )
    private List<Aluno> alunos = new ArrayList<>();

}
