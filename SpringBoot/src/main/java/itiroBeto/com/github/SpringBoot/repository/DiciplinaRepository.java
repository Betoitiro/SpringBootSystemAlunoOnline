package itiroBeto.com.github.SpringBoot.repository;

import itiroBeto.com.github.SpringBoot.model.Diciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiciplinaRepository extends JpaRepository<Diciplina, Long> {
    List<Diciplina>findByProfessorId (Long professorId);

    //Lista de diciplinas de professor id
}
