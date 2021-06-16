package com.example.modules.classes.domain;

import com.example.modules.student.domain.Student;
import com.example.modules.subject.domain.Subject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="class_id")
    private int classId;

    @Column(name="name", nullable = false, length = 30)
    private String name;

    @ManyToMany(mappedBy = "classList", cascade = CascadeType.ALL
            , fetch = FetchType.LAZY)
    private Set<Subject> subjectList = new HashSet<>();

    @OneToMany(targetEntity = Student.class, cascade =  CascadeType.ALL)
    private List<Student> studentList;

    public Class() {
    }

    public Class(String name, Set<Subject> subjectList) {
        this.name = name;
        this.subjectList = subjectList;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int id) {
        this.classId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(Set<Subject> subjectList) {
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
                "id=" + classId +
                ", name='" + name + '\'' +
                '}';
    }
}
