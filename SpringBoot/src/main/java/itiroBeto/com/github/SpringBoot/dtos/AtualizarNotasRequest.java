package itiroBeto.com.github.SpringBoot.dtos;



//Lombok data n funciona
public class AtualizarNotasRequest {
    private Double nota1;
    private Double nota2;

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }
}
