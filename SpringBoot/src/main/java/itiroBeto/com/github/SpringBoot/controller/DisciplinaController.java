package itiroBeto.com.github.SpringBoot.controller;

import itiroBeto.com.github.SpringBoot.model.Disciplina;
import itiroBeto.com.github.SpringBoot.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diciplina")
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Disciplina disciplina){
        disciplinaService.create(disciplina);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Disciplina> findAll(){
        return disciplinaService.FindAll();
    }

    @GetMapping("/professor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Disciplina> findByIdProfessor(@PathVariable Long id){
        return disciplinaService.findByIdProfessor(id);
    }

}
