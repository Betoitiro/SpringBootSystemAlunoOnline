package itiroBeto.com.github.SpringBoot.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

//Não coloquei o lombok pois n funciona no codigo
@Entity
public class Fatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_Financial_id")
    private FinanceiroAluno studentFinancial;


    private LocalDateTime dueDate;

    private LocalDateTime paidOn;

    private LocalDateTime createAt;

}
