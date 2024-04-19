package itiroBeto.com.github.SpringBoot.model;

import itiroBeto.com.github.SpringBoot.enums.MatriculaAlunoStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatriculaAluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double nota1;
    private double nota2;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "diciplina_id")
    Diciplina diciplina;

    @Enumerated(EnumType.STRING)
    private MatriculaAlunoStatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Diciplina getDiciplina() {
        return diciplina;
    }

    public void setDiciplina(Diciplina diciplina) {
        this.diciplina = diciplina;
    }

    public MatriculaAlunoStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MatriculaAlunoStatusEnum status) {
        this.status = status;
    }
}
