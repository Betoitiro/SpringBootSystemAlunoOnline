package itiroBeto.com.github.SpringBoot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

//Estrutura do objeto

//Construtores

/**
 * tirei o
 * @AllArgsConstructor
 * @NoArgsConstructor
 *
 * pois estava dando erro
 */
@Data//Faz os getrs and setrs automaticamente, graças ao lombok
@Entity//faz com que o spring veja a classe como entidade

public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String email;

    @OneToOne
    @JoinColumn(name = "couse_id")
    private Curso course;

    public Aluno() {
    }

    public Aluno(Long id, String name, String email, Curso course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
