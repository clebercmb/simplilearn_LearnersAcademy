package com.example.modules.classes.dto;

import java.util.Set;

public class SubjectDto {

    private int subjectId;
    private String name;
    private Set<TeacherDto> teachers;

    public SubjectDto() {
    }

    public SubjectDto(int subjectId, String name, Set<TeacherDto> teachers) {
        this.subjectId = subjectId;
        this.name = name;
        this.teachers = teachers;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TeacherDto> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeacherDto> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(TeacherDto teacherDto) {
        getTeachers().add(teacherDto);
    }

    public void removeTeaacher(TeacherDto teacherDto) {
        getTeachers().remove(teacherDto);
    }

}
