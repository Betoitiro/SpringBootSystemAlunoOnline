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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FinanceiroAluno getStudentFinancial() {
        return studentFinancial;
    }

    public void setStudentFinancial(FinanceiroAluno studentFinancial) {
        this.studentFinancial = studentFinancial;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(LocalDateTime paidOn) {
        this.paidOn = paidOn;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
