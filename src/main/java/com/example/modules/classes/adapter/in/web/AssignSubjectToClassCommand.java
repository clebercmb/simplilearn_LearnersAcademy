package com.example.modules.classes.adapter.in.web;

import com.example.modules.classes.domain.Class;
import com.example.modules.subject.domain.Subject;
import com.example.modules.subject.dto.SubjectSelectedViewDto;

import java.util.List;

public class AssignSubjectToClassCommand {


    private Class aClass;
    private List<SubjectSelectedViewDto> subjectList;
    private List<Integer> subjectListIds;
    private Integer idClass;


    public AssignSubjectToClassCommand() {
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public List<SubjectSelectedViewDto> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectSelectedViewDto> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Integer> getSubjectListIds() {
        return subjectListIds;
    }

    public void setSubjectListIds(List<Integer> subjectListIds) {
        this.subjectListIds = subjectListIds;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }
}
