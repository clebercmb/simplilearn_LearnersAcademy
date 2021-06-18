package com.example.modules.classes.domain;

import com.example.modules.student.domain.Student;
import com.example.modules.subject.domain.Subject;
import com.example.modules.teacher.domain.Teacher;

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

    @ManyToMany(mappedBy = "classList", fetch = FetchType.EAGER)
    private Set<Subject> subjectList = new HashSet<>();

    @ManyToMany(mappedBy = "teacherClassList", fetch = FetchType.LAZY)
    private Set<Teacher> teacherList = new HashSet<>();

    @OneToMany(targetEntity = Student.class, cascade =  CascadeType.ALL)
    private List<Student> studentList;

    @OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ClassSubjectTeacherLink> classSubjectTeacherLinks;


    public Class() {
    }

    public Class(int classId, String name, Set<Subject> subjectList, Set<Teacher> teacherList, List<Student> studentList) {
        this.classId = classId;
        this.name = name;
        this.subjectList = subjectList;
        this.teacherList = teacherList;
        this.studentList = studentList;
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

    public Set<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(Set<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public Set<ClassSubjectTeacherLink> getClassSubjectTeacherLinks() {
        return classSubjectTeacherLinks;
    }

    public void setClassSubjectTeacherLinks(Set<ClassSubjectTeacherLink> classSubjectTeacherLinks) {
        this.classSubjectTeacherLinks = classSubjectTeacherLinks;
    }

    public void addSubject(Subject subject) {
        this.subjectList.add(subject);
        subject.getClassList().add(this);
    }

    public void removeSubject(Subject subject) {
        this.subjectList.remove(subject);
        subject.getClassList().remove(this);
    }

    public void addTeacher(Teacher teacher) {
        this.teacherList.add(teacher);
        teacher.getTeacherClassList().add(this);
    }

    public void removeTeacher(Teacher teacher) {
        this.teacherList.remove(teacher);
        teacher.getTeacherClassList().remove(this);
    }


    @Override
    public String toString() {
        return "Class{" +
                "id=" + classId +
                ", name='" + name + '\'' +
                '}';
    }
}
