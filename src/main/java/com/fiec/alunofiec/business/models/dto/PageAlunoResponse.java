package com.fiec.alunofiec.business.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageAlunoResponse {
    int totalPages;
    List<AlunoResponse> alunos;
}
