package itiroBeto.com.github.SpringBoot.repository;

import itiroBeto.com.github.SpringBoot.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoResitory extends JpaRepository<Curso, Long> {
}
