package itiroBeto.com.github.SpringBoot.controller;

import itiroBeto.com.github.SpringBoot.dtos.AtualizarNotasRequest;
import itiroBeto.com.github.SpringBoot.dtos.HistoricoAlunoResponse;
import itiroBeto.com.github.SpringBoot.model.MatriculaAluno;
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

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MatriculaAluno> findAll(){
        return matriculaAlunoService.findAll();
    }

    @PatchMapping("/updatedGrades/{matriculaAlunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGrades(@RequestBody AtualizarNotasRequest atualizarNotasRequest,
                             @PathVariable Long matriculaAlunoId){
        matriculaAlunoService.updateGrades(matriculaAlunoId, atualizarNotasRequest);
    }

    @PatchMapping("/updatedStatusToBreak/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStateToBreak(@PathVariable Long id){
        matriculaAlunoService.updateStatusToBreak(id);
    }

    @GetMapping("/academic-transcript/{alunoId}")
    @ResponseStatus(HttpStatus.OK)
    public HistoricoAlunoResponse getAcademicTranscript(@PathVariable Long alunoId) {
        return matriculaAlunoService.getAcademicTranscript(alunoId);
    }
}
