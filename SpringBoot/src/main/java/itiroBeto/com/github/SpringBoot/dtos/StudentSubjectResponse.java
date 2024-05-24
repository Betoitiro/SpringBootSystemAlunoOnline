package itiroBeto.com.github.SpringBoot.dtos;

import itiroBeto.com.github.SpringBoot.enums.MatriculaAlunoStatusEnum;
import lombok.Data;
import org.springframework.stereotype.Service;


@Data


public class StudentSubjectResponse {
    private String subjectName;
    private String professor;
    private Double grade1;
    private Double grade2;
    private Double average;
    private MatriculaAlunoStatusEnum status;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
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

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public MatriculaAlunoStatusEnum getStatus() {
        return status;
    }

    public void setStatus(MatriculaAlunoStatusEnum status) {
        this.status = status;
    }
}

