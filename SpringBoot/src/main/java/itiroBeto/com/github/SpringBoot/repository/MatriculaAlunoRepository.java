package itiroBeto.com.github.SpringBoot.repository;

import itiroBeto.com.github.SpringBoot.model.MatriculaAluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MatriculaAlunoRepository extends JpaRepository<MatriculaAluno, Long> {

    List<MatriculaAluno> findByAlunoId(Long alunoId);
}
