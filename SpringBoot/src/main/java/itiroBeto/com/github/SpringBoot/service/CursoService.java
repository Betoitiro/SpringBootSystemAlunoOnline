package itiroBeto.com.github.SpringBoot.service;

import itiroBeto.com.github.SpringBoot.model.Curso;
import itiroBeto.com.github.SpringBoot.repository.CursoResitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    CursoResitory cursoResitory;
    public void create(Curso curso){
        cursoResitory.save(curso);
    }
}
