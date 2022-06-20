package com.fiec.alunofiec.business.models.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_presencas")
public class Presenca {

    @Id
    @GeneratedValue
    private int id;

    @CreationTimestamp
    Date horaDaPresenca;

    @UpdateTimestamp
    Date atualizacaoPresenca;

    @ManyToOne
    Aluno aluno;

    @ManyToOne
    Materia materia;

    int numAula;

    double lat;
    double lng;
}
