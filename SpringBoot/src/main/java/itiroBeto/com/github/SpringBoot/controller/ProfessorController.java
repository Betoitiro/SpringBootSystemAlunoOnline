package itiroBeto.com.github.SpringBoot.controller;

import itiroBeto.com.github.SpringBoot.model.Professor;
import itiroBeto.com.github.SpringBoot.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Professor professor){
        professorService.createProfessor(professor);
    }

    @PutMapping("(/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updated(@RequestBody Professor professor, @PathVariable Long id){
        professorService.updateProfessor(id, professor);
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Professor> findAll(){
        return professorService.findAllProfessors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Professor> findByid(@PathVariable Long id){
        return professorService.findProfessorById(id);
    }



    @DeleteMapping("/delete /{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByid(@PathVariable Long id){
        professorService.deleteProfessorById(id);
    }



}
