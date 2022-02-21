package com.fiec.alunofiec.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    private int rm;
    private String nome;
    private String curso;
    private String profileImage;
}
