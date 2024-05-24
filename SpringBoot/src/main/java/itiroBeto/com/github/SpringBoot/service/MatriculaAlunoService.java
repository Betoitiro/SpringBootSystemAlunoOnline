package itiroBeto.com.github.SpringBoot.service;

import itiroBeto.com.github.SpringBoot.dtos.AtualizarNotasRequest;
import itiroBeto.com.github.SpringBoot.dtos.HistoryStudentResponse;
import itiroBeto.com.github.SpringBoot.dtos.StudentSubjectResponse;
import itiroBeto.com.github.SpringBoot.enums.MatriculaAlunoStatusEnum;
import itiroBeto.com.github.SpringBoot.model.MatriculaAluno;
import itiroBeto.com.github.SpringBoot.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaAlunoService {

    public static final double GRADE_AVG_TO_APPROVE = 7.0;

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void create(MatriculaAluno matriculaAluno){
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public List<MatriculaAluno> findALl(){
        return matriculaAlunoRepository.findAll();
    }



    //outa maneira de buscar por id, sem ser usando optional
    public MatriculaAluno findById(Long id){
        return matriculaAlunoRepository.findById(id).orElse(null);
    }

    public void updateGrade(Long matriculaAlunoId, AtualizarNotasRequest atualizarNotasRequest){
        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(matriculaAlunoId).orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus
                                .NOT_FOUND, "Matricula não encontrada!"));

        updateStudentGrades(matriculaAluno, atualizarNotasRequest);
        updateStudentStatus(matriculaAluno);

        matriculaAlunoRepository.save(matriculaAluno);


    }

    public void updateStudentGrades(MatriculaAluno matriculaAluno, AtualizarNotasRequest atualizarNotasRequest){
        if (atualizarNotasRequest.getGrade1() != null){
            matriculaAluno.setGrade1(atualizarNotasRequest.getGrade1());
        }
        if (atualizarNotasRequest.getGrade2()!= null) {
            matriculaAluno.setGrade2(atualizarNotasRequest.getGrade2());
        }
    }

    public void updateStudentStatus(MatriculaAluno matriculaAluno){
        Double grade1 = matriculaAluno.getGrade1();
        Double grade2 = matriculaAluno.getGrade2();


        if(grade1 != null &&  grade2 != null){
            double average = (grade1 + grade2) / 2;


            //if como operador ternario
            // ? -> if
            // : -> else
            matriculaAluno.setStatus(average >= GRADE_AVG_TO_APPROVE ?
                    MatriculaAlunoStatusEnum.APROVADO
                    : MatriculaAlunoStatusEnum.REPROVADO);
        }

    }

    public void updatedStatusToBreak(Long matriculaId){
        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(matriculaId).
                        orElseThrow(()->
                                new ResponseStatusException(HttpStatus.NOT_FOUND,"Matricula não encontrada"));

        if (!MatriculaAlunoStatusEnum.MATRICULADO .equals(matriculaAluno.getStatus())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só possivel trancar uma matricula com o Status matriculado");
        }
        changeStatus(matriculaAluno, MatriculaAlunoStatusEnum.TRANCADO);
    }

    public void changeStatus(MatriculaAluno matriculaAluno, MatriculaAlunoStatusEnum matriculaAlunoStatusEnum){
        matriculaAluno.setStatus(matriculaAlunoStatusEnum);
        matriculaAlunoRepository.save(matriculaAluno);
    }


    public HistoryStudentResponse getAcademicTranscript(Long alunoId) {
        List<MatriculaAluno> matriculasDoAluno = matriculaAlunoRepository.findByStudentId(alunoId);

        if(matriculasDoAluno.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse aluno não possui matrículas");
        }

        HistoryStudentResponse historico = new HistoryStudentResponse();
        historico.setStudentName(matriculasDoAluno.get(0).getStudent().getName());
        historico.setStudentEmail(matriculasDoAluno.get(0).getStudent().getEmail());

        List<StudentSubjectResponse> disciplinasList = new ArrayList<>();

        for (MatriculaAluno matricula : matriculasDoAluno) {
            StudentSubjectResponse disciplinasAlunoResponse = new StudentSubjectResponse();
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
