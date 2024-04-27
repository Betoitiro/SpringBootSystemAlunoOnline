package itiroBeto.com.github.SpringBoot.dtos;

import itiroBeto.com.github.SpringBoot.enums.MatriculaAlunoStatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StudentSubjectResponse {
    private String diciplinaAluno;
    private Double nota1;
    private Double nota2;
    private Double media;
    private MatriculaAlunoStatusEnum status;

}
