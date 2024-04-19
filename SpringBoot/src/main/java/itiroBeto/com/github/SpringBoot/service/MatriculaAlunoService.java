package itiroBeto.com.github.SpringBoot.service;

import itiroBeto.com.github.SpringBoot.dtos.AtualizarNotasRequest;
import itiroBeto.com.github.SpringBoot.enums.MatriculaAlunoStatusEnum;
import itiroBeto.com.github.SpringBoot.model.MatriculaAluno;
import itiroBeto.com.github.SpringBoot.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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



    }

    public void updateStudentGrades(MatriculaAluno matriculaAluno, AtualizarNotasRequest atualizarNotasRequest){
        if (atualizarNotasRequest.getNota1() != null){
            matriculaAluno.setNota1(atualizarNotasRequest.getNota1());
        }
        if (atualizarNotasRequest.getNota2()!= null) {
            matriculaAluno.setNota2(atualizarNotasRequest.getNota2());
        }
    }

    public void updateStudentStatus(MatriculaAluno matriculaAluno){
        Double nota1 = matriculaAluno.getNota1();
        Double nota2 = matriculaAluno.getNota2();


        if (nota1 != null && nota2 != null){
            double average = (nota1 + nota2) / 2;


            //if como operador ternario
            // ? -> if
            // : -> else
            matriculaAluno.setStatus(average >= GRADE_AVG_TO_APPROVE ?
                    MatriculaAlunoStatusEnum.APROVADO
                    : MatriculaAlunoStatusEnum.REPROVADO);
        }

    }

}
