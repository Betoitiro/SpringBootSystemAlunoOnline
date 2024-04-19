package itiroBeto.com.github.SpringBoot.service;

import itiroBeto.com.github.SpringBoot.enums.MatriculaAlunoStatusEnum;
import itiroBeto.com.github.SpringBoot.model.MatriculaAluno;
import itiroBeto.com.github.SpringBoot.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaAlunoService {

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

    public

}
