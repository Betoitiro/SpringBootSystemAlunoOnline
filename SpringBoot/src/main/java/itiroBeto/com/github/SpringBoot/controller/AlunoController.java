package itiroBeto.com.github.SpringBoot.controller;

import itiroBeto.com.github.SpringBoot.model.Aluno;
import itiroBeto.com.github.SpringBoot.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//tem que ter a noção teorica de http e restfull

@RestController //Estar sinalizando ao Spring que esta classe é uma aplicação RestFull

@RequestMapping("/aluno") //Vai mapear o /aluno

public class AlunoController {

    @Autowired //injetando as regras de negocio dentro do controller
    AlunoService alunoService;

    //verbos http (Post Get)
    //Post - criar
    //Get - pegar

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //converte o 201 (conexão bem sucedida) em create
    public void create(@RequestBody Aluno aluno) //converte o json em objeto java (aluno)
    {

        alunoService.create(aluno); //aqui o objeto aluno java, não esta mais como json
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> findAll(){
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Aluno> findById(@PathVariable Long id){
        return alunoService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Aluno aluno, @PathVariable Long id){
        alunoService.update(id, aluno);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByid(@PathVariable Long id){
        alunoService.deleteById(id);
    }
}
