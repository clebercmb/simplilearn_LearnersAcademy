package com.example.modules.classes.domain;

import com.example.modules.student.domain.Student;
import com.example.modules.subject.domain.Subject;

import java.util.List;

public class Class {
    private int id;
    private String name;
    private List<Subject> subjectList;
    private List<Student> studentList;


    public Class() {
    }

    public Class(String name, List<Subject> subjectList) {
        this.name = name;
        this.subjectList = subjectList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
