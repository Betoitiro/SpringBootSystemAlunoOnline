package itiroBeto.com.github.SpringBoot.repository;

import itiroBeto.com.github.SpringBoot.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    List<Disciplina>findByProfessorId (Long professorId);

    //Lista de diciplinas de professor id
}
