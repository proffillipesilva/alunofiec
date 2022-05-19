package com.fiec.alunofiec.services;

import com.fiec.alunofiec.business.models.entities.Aula;
import com.fiec.alunofiec.business.repositories.IAulaRepositorio;
import com.fiec.alunofiec.business.repositories.IMateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AulaService implements IAulaService{

    @Autowired
    IAulaRepositorio aulaRepositorio;

    @Autowired
    IMateriaRepositorio materiaRepositorio;

    @Override
    public Page<Aula> getAulas(int page, int pageSize) {
        return aulaRepositorio.findAll(PageRequest.of(page,pageSize));
    }

    @Override
    public void saveAula(Aula aula) {
        aulaRepositorio.save(aula);
    }

    @Override
    public void atualizaAula(Aula aula, String id) {
        Aula aulaAnterior = aulaRepositorio.findById(id).orElse(null);
        aulaAnterior.setDiaAula(aula.getDiaAula());
        aulaAnterior.setDiaAula(aula.getDiaAula());
        aulaAnterior.setNumAula(aula.getNumAula());
        aulaRepositorio.save(aulaAnterior);
    }

    @Override
    public Aula pegaAula(String id) {
        return aulaRepositorio.findById(id).orElseThrow();
    }

    @Override
    public void deletaAula(String id) {
        aulaRepositorio.deleteById(id);
    }
}
