package itiroBeto.com.github.SpringBoot.repository;

import itiroBeto.com.github.SpringBoot.model.FinanceiroAluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceiroAlunoRepository extends JpaRepository<FinanceiroAluno, Long> {
}
