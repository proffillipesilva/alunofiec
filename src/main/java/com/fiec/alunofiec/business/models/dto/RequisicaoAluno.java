package com.fiec.alunofiec.business.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequisicaoAluno {

    String rm;
    String email;
    String nome;
    String curso;
    String profileImage;
}
