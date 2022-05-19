package com.fiec.alunofiec.business.models.dto;

import lombok.Data;

@Data
public class SendMessageRequest {

    private String title;
    private String message;
    private String cursoId;
    private String alunoId;
}
