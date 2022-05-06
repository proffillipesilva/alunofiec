package com.fiec.alunofiec.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiec.alunofiec.business.models.dto.AlunoResponse;
import com.fiec.alunofiec.business.models.dto.PageAlunoResponse;
import com.fiec.alunofiec.business.models.dto.RequisicaoAluno;
import com.fiec.alunofiec.business.models.entities.Aluno;
import com.fiec.alunofiec.business.models.entities.User;
import com.fiec.alunofiec.business.repositories.IUserRepositorio;
import com.fiec.alunofiec.services.IAlunoService;
import com.fiec.alunofiec.services.JwtUserDetailsService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    IAlunoService alunoService;

    @Autowired
    IUserRepositorio userRepositorio;

    @GetMapping
    public PageAlunoResponse getAlunos(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize){
        Page<Aluno> alunos = alunoService.getAlunos(page, pageSize);
        List<AlunoResponse> alunoResponseList = alunos.stream()
                .map(aluno -> AlunoResponse.builder()
                        .rm(aluno.getRm())
                        .nome(aluno.getUser().getNome())
                        .curso(aluno.getCurso())
                        .profileImage(aluno.getUser().getProfileImage())
                        .build()
                ).collect(Collectors.toList());
        return PageAlunoResponse.builder()
                .alunos(alunoResponseList)
                .totalPages(alunos.getTotalPages()).build();
    }

    @PostMapping
    public void saveAluno(@RequestBody Aluno aluno){
        alunoService.saveAluno(aluno);
    }

    @GetMapping("/{alunoId}")
    public Aluno pegaAluno(@PathVariable("alunoId") String id){

        return alunoService.pegaAluno(id);
    }

    @PutMapping("/{alunoId}")
    public void atualizaAluno(@PathVariable("alunoId") String id, @RequestBody Aluno aluno){
        alunoService.atualizaAluno(aluno, id);
    }

    @DeleteMapping("/{alunoId}")
    public void deletaAluno(@PathVariable("alunoId") String id){
        alunoService.deletaAluno(id);
    }

    @PostMapping(value = "/comFoto",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveAlunoComFoto(@RequestParam("aluno") String aluno, @RequestParam("foto") MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        RequisicaoAluno requisicaoAluno = objectMapper.readValue(aluno, RequisicaoAluno.class);
        Aluno novoAluno = new Aluno();
        novoAluno.setCurso(requisicaoAluno.getCurso());
        novoAluno.setRm(Integer.parseInt(requisicaoAluno.getRm()));
        User user = userRepositorio.findByEmail(requisicaoAluno.getEmail()).orElse(null);
        user.setNome(requisicaoAluno.getNome());
        novoAluno.setUser(user);
        String profileImage = UUID.randomUUID() + "_" + Long.toHexString(new Date().getTime());
        novoAluno.getUser().setProfileImage(profileImage + ".jpg");
        alunoService.saveAluno(novoAluno);

        Path filename = Paths.get("uploads").resolve(profileImage);

        Path thumbFilename = Paths.get("uploads").resolve("thumb_" + profileImage);
        Thumbnails.of(file.getInputStream())
                .size(500, 500)
                .outputFormat("jpg")
                .toFile(new File(filename.toString()));
        Thumbnails.of(file.getInputStream())
                .size(100, 100)
                .outputFormat("jpg")
                .toFile(new File(thumbFilename.toString()));


    }




}
