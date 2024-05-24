package itiroBeto.com.github.SpringBoot.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoricoAlunoResponse {
    private String studentName;

    private String studentEmail;

    private List<DisciplinasAlunoResponse> studentSubjectsResponseList;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public List<DisciplinasAlunoResponse> getStudentSubjectsResponseList() {
        return studentSubjectsResponseList;
    }

    public void setStudentSubjectsResponseList(List<DisciplinasAlunoResponse> studentSubjectsResponseList) {
        this.studentSubjectsResponseList = studentSubjectsResponseList;
    }
}
