package itiroBeto.com.github.SpringBoot.model;

import itiroBeto.com.github.SpringBoot.enums.MatriculaAlunoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
/***
 * NÃO FUNCIONA
 *
 *@AllArgsConstructor
 * @NoArgsConstructo
 */

@Data
public class MatriculaAluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double grade1;

    private Double grade2;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Aluno student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Disciplina subject;

    @Enumerated(EnumType.STRING)
    private MatriculaAlunoStatusEnum status;

    public MatriculaAluno() {
    }

    public MatriculaAluno(Long id, Double grade1, Double grade2, Aluno student, Disciplina subject, MatriculaAlunoStatusEnum status) {
        this.id = id;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.student = student;
        this.subject = subject;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGrade1() {
        return grade1;
    }

    public void setGrade1(Double grade1) {
        this.grade1 = grade1;
    }

    public Double getGrade2() {
        return grade2;
    }

    public void setGrade2(Double grade2) {
        this.grade2 = grade2;
    }

    public Aluno getStudent() {
        return student;
    }

    public void setStudent(Aluno student) {
        this.student = student;
    }

    public Disciplina getSubject() {
        return subject;
    }

    public void setSubject(Disciplina subject) {
        this.subject = subject;
    }

    public MatriculaAlunoStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MatriculaAlunoStatusEnum status) {
        this.status = status;
    }
}
