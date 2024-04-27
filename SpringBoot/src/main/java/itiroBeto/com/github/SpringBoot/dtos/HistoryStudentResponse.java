package itiroBeto.com.github.SpringBoot.dtos;

import lombok.Data;

import java.util.List;

@Data
public class HistoryStudentResponse {
    private String studentName;

    private String studentEmail;

    private List<StudentSubjectResponse> studentSubjectsResponseList;

}
