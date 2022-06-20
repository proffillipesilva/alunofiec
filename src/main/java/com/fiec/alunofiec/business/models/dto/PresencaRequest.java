package com.fiec.alunofiec.business.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PresencaRequest {

    int numAula;
    String materiaId;
    double lat;
    double lng;
}
