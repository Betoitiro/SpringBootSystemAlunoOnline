package itiroBeto.com.github.SpringBoot.controller;

import itiroBeto.com.github.SpringBoot.model.Curso;
import itiroBeto.com.github.SpringBoot.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoService cursoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Curso curso){
        cursoService.create(curso);
    }


}
