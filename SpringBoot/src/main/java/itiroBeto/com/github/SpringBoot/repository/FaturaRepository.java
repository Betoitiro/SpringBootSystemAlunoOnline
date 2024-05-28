package itiroBeto.com.github.SpringBoot.repository;

import itiroBeto.com.github.SpringBoot.model.Fatura;
import itiroBeto.com.github.SpringBoot.model.FinanceiroAluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface FaturaRepository extends JpaRepository<Fatura, Long> {
    boolean existsByStudentFinancialAndDueDate(FinanceiroAluno studentFinancial, LocalDateTime dueDate);

}
