package com.fiec.alunofiec.controllers;

import com.fiec.alunofiec.business.models.entities.Aula;
import com.fiec.alunofiec.services.IAulaService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    @Autowired
    IAulaService aulaService;

    @GetMapping
    public GetAulasResponse getAulas(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        Page<Aula> aulas = aulaService.getAulas(page, pageSize);
        return GetAulasResponse.builder()
                .aulas(aulas.toList())
                .totalPages(aulas.getTotalPages())
                .build();

    }

    @Data
    @Builder
    static class GetAulasResponse {
        List<Aula> aulas;
        int totalPages;
    }





}
