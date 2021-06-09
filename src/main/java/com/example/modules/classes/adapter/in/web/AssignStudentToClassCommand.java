package com.example.modules.classes.adapter.in.web;

import com.example.modules.classes.domain.Class;
import com.example.modules.student.dto.StudentSelectedViewDto;
import com.example.modules.subject.dto.SubjectSelectedViewDto;

import java.util.List;

public class AssignStudentToClassCommand {


    private Class aClass;
    private List<StudentSelectedViewDto> studentList;
    private List<Integer> studentListIds;
    private Integer idClass;


    public AssignStudentToClassCommand() {
    }


    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public List<StudentSelectedViewDto> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentSelectedViewDto> studentList) {
        this.studentList = studentList;
    }

    public List<Integer> getStudentListIds() {
        return studentListIds;
    }

    public void setStudentListIds(List<Integer> studentListIds) {
        this.studentListIds = studentListIds;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }
}
