package com.fiec.alunofiec.business.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlunoResponse {

    int rm;
    String nome;
    String curso;
    String profileImage;
}
