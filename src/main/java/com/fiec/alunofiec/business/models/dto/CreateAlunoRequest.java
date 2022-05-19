package com.fiec.alunofiec.business.models.dto;

import lombok.Data;

@Data
public class CreateAlunoRequest {
    String googleToken;
    int rm;
    String nome;
    String curso;
}
