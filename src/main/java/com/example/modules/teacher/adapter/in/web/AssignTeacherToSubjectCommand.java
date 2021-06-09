package com.example.modules.teacher.adapter.in.web;


import com.example.modules.subject.dto.SubjectSelectedViewDto;
import com.example.modules.teacher.domain.Teacher;

import java.util.List;

public class AssignTeacherToSubjectCommand {

    private Teacher teacher;
    private List<SubjectSelectedViewDto> subjectList;
    private List<Integer> subjectListIds;
    private Integer idTeacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }
}
