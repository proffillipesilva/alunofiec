package com.fiec.alunofiec.business.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PresencaResponse {

    int numAula;
    String materiaId;
}
