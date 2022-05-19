package com.fiec.alunofiec.controllers;

import com.fiec.alunofiec.business.models.dto.AlunoResponse;
import com.fiec.alunofiec.business.models.dto.CreateAlunoRequest;
import com.fiec.alunofiec.business.models.dto.PageAlunoResponse;
import com.fiec.alunofiec.business.models.entities.Aluno;
import com.fiec.alunofiec.business.models.entities.Materia;
import com.fiec.alunofiec.business.models.entities.Materia;
import com.fiec.alunofiec.business.repositories.IUserRepositorio;
import com.fiec.alunofiec.services.IAlunoService;
import com.fiec.alunofiec.services.IMateriaService;
import lombok.Builder;
import lombok.Data;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    
   
    @Autowired
    IMateriaService materiaService;

    @GetMapping
    public MateriaController.GetMateriasResponse getMaterias(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        Page<Materia> materias = materiaService.getMaterias(page, pageSize);
        return MateriaController.GetMateriasResponse.builder()
                .materias(materias.toList())
                .totalPages(materias.getTotalPages())
                .build();

    }


    @Data
    @Builder
    static class GetMateriasResponse {
        List<Materia> materias;
        int totalPages;
    }





}
