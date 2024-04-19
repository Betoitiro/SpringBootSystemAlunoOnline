package itiroBeto.com.github.SpringBoot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

//Estrutura do objeto

//Construtores
@AllArgsConstructor
@NoArgsConstructor
@Data//Faz os getrs and setrs automaticamente, graças ao lombok
@Entity//faz com que o spring veja a classe como entidade

public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String course;
    private String email;

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
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
