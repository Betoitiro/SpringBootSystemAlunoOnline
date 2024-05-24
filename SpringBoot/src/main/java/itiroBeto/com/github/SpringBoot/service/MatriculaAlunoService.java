package itiroBeto.com.github.SpringBoot.service;

import itiroBeto.com.github.SpringBoot.dtos.AtualizarNotasRequest;
import itiroBeto.com.github.SpringBoot.dtos.HistoricoAlunoResponse;
import itiroBeto.com.github.SpringBoot.dtos.DisciplinasAlunoResponse;
import itiroBeto.com.github.SpringBoot.enums.MatriculaAlunoStatusEnum;
import itiroBeto.com.github.SpringBoot.model.MatriculaAluno;
import itiroBeto.com.github.SpringBoot.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaAlunoService {


    public static final double GRADE_AVG_TO_APPROVE = 7.0;

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void create(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MatriculaAluno> findAll(){
        return matriculaAlunoRepository.findAll();
    }

    public void updateGrades(Long matriculaAlunoId, AtualizarNotasRequest atualizarNotasRequest) {
        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(matriculaAlunoId)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada"));
        updateStudentGrades(matriculaAluno, atualizarNotasRequest);
        updateStudentStatus(matriculaAluno);

        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void updateStudentGrades(MatriculaAluno matriculaAluno, AtualizarNotasRequest atualizarNotasRequest) {
        if (atualizarNotasRequest.getGrade1() != null) {
            matriculaAluno.setGrade1(atualizarNotasRequest.getGrade1());
        }

        if (atualizarNotasRequest.getGrade2() != null) {
            matriculaAluno.setGrade2(atualizarNotasRequest.getGrade2());
        }
    }

    public void updateStudentStatus(MatriculaAluno matriculaAluno) {
        Double nota1 = matriculaAluno.getGrade1();
        Double nota2 = matriculaAluno.getGrade2();

        if (nota1 != null && nota2 != null) {
            double average = (nota1 + nota2) / 2;
            matriculaAluno.setStatus(average >= GRADE_AVG_TO_APPROVE ? MatriculaAlunoStatusEnum.APROVADO : MatriculaAlunoStatusEnum.REPROVADO);
        }

    }

    public void updateStatusToBreak(Long matriculaAlunoId) {
        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(matriculaAlunoId)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada"));

        if (!MatriculaAlunoStatusEnum.MATRICULADO.equals(matriculaAluno.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível trancar uma matrícula com o status MATRICULADO");
        }

        changeStatus(matriculaAluno, MatriculaAlunoStatusEnum.TRANCADO);
    }

    public void changeStatus(MatriculaAluno matriculaAluno, MatriculaAlunoStatusEnum matriculaAlunoStatusEnum) {
        matriculaAluno.setStatus(matriculaAlunoStatusEnum);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public HistoricoAlunoResponse getAcademicTranscript(Long alunoId) {
        List<MatriculaAluno> matriculasDoAluno = matriculaAlunoRepository.findByStudentId(alunoId);

        if(matriculasDoAluno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não possui matrículas");
        }

        HistoricoAlunoResponse historico = new HistoricoAlunoResponse();
        historico.setStudentName(matriculasDoAluno.get(0).getStudent().getName());
        historico.setStudentEmail(matriculasDoAluno.get(0).getStudent().getEmail());

        List<DisciplinasAlunoResponse> disciplinasList = new ArrayList<>();

        for (MatriculaAluno matricula : matriculasDoAluno) {
            DisciplinasAlunoResponse disciplinasAlunoResponse = new DisciplinasAlunoResponse();
            disciplinasAlunoResponse.setSubjectName(matricula.getSubject().getName());
            disciplinasAlunoResponse.setProfessor(matricula.getSubject().getProfessor().getName());
            disciplinasAlunoResponse.setGrade1(matricula.getGrade1());
            disciplinasAlunoResponse.setGrade2(matricula.getGrade2());

            // não quero isso nese método, MAS eu (prof) vou fazer
            if(matricula.getGrade1() != null && matricula.getGrade2() != null) {
                disciplinasAlunoResponse.setAverage((matricula.getGrade1() + matricula.getGrade2()) / 2.0);
            } else {
                disciplinasAlunoResponse.setAverage(null);
            }

            disciplinasAlunoResponse.setStatus(matricula.getStatus());
            disciplinasList.add(disciplinasAlunoResponse);
        }

        historico.setStudentSubjectsResponseList(disciplinasList);

        return historico;
    }



}
