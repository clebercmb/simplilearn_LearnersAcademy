package com.example.modules.classes.adapter.in.web;

import com.example.modules.classes.dto.ClassDto;
import com.example.modules.subject.domain.Subject;
import com.example.modules.classes.domain.Class;

import java.util.List;

public class AssignTeacherToClassCommand {

    private ClassDto aClass;

    private List<Subject> subjectList;

    private List<String> teacherListId;
    private Integer idClass;

    public ClassDto getaClass() {
        return aClass;
    }

    public void setaClass(ClassDto aClass) {
        this.aClass = aClass;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<String> getTeacherListId() {
        return teacherListId;
    }

    public void setTeacherListId(List<String> teacherListId) {
        this.teacherListId = teacherListId;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }
}
