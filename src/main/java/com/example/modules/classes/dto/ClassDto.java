package com.example.modules.classes.dto;

import java.util.Set;

public class ClassDto {

    private int classId;
    private String name;
    private Set<SubjectDto> subjects;
    private Set<StudentDto> students;


    public ClassDto() {
    }

    public ClassDto(int classId, String name, Set<SubjectDto> subjects, Set<StudentDto> students) {
        this.classId = classId;
        this.name = name;
        this.subjects = subjects;
        this.students = students;
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

    public Set<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentDto> students) {
        this.students = students;
    }

    public void addSubject(SubjectDto subject) {
        getSubjects().add(subject);
    }

    public void removeSubject(SubjectDto subject) {
        getSubjects().remove(subject);
    }

    public void addStudent(StudentDto student) {
        getStudents().add(student);
    }

    public void removeStudent(StudentDto student) {
        getStudents().remove(student);
    }
}
