package itiroBeto.com.github.SpringBoot.controller;

import itiroBeto.com.github.SpringBoot.model.MatriculaAluno;
import itiroBeto.com.github.SpringBoot.repository.MatriculaAlunoRepository;
import itiroBeto.com.github.SpringBoot.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService matriculaAlunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody MatriculaAluno matriculaAluno){
        matriculaAlunoService.create(matriculaAluno);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MatriculaAluno> findAll(){
        return matriculaAlunoService.findALl();
    }

}
