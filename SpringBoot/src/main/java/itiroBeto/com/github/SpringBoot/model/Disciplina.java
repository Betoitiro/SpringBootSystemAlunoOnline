package itiroBeto.com.github.SpringBoot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/***
 * NÃO FUNCIONA
 *
 *@AllArgsConstructor
 * @NoArgsConstructo
 */

@Data
@Entity
public class Disciplina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public Disciplina() {
    }

    public Disciplina(Long id, String name, Professor professor) {
        this.id = id;
        this.name = name;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
