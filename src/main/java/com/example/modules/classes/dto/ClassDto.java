package com.example.modules.classes.dto;

import java.util.Set;

public class ClassDto {

    private int classId;
    private String name;
    private Set<SubjectDto> subjects;

    public ClassDto() {
    }

    public ClassDto(int classId, String name, Set<SubjectDto> subjects) {
        this.classId = classId;
        this.name = name;
        this.subjects = subjects;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectDto> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(SubjectDto subject) {
        getSubjects().add(subject);
    }

    public void removeSubject(SubjectDto subject) {
        getSubjects().remove(subject);
    }
}
