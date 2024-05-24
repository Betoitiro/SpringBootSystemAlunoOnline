package itiroBeto.com.github.SpringBoot.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoryStudentResponse {
    private String studentName;

    private String studentEmail;

    private List<StudentSubjectResponse> studentSubjectsResponseList;

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

    public List<StudentSubjectResponse> getStudentSubjectsResponseList() {
        return studentSubjectsResponseList;
    }

    public void setStudentSubjectsResponseList(List<StudentSubjectResponse> studentSubjectsResponseList) {
        this.studentSubjectsResponseList = studentSubjectsResponseList;
    }
}
