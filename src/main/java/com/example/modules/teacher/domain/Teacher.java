package com.example.modules.teacher.domain;

import com.example.modules.classes.domain.ClassSubjectTeacherLink;
import com.example.modules.subject.domain.Subject;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import com.example.modules.classes.domain.Class;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacher_id")
    private int teacherId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "teacher_subject", joinColumns = { @JoinColumn(name = "teacher_id") }, inverseJoinColumns = {
            @JoinColumn(name = "subject_id") })
    private Set<Subject> subjectList;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "teacher_class", joinColumns = { @JoinColumn(name = "teacher_id") }, inverseJoinColumns = {
            @JoinColumn(name = "class_id") })
    private Set<Class> teacherClassList;


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<ClassSubjectTeacherLink> classSubjectTeacherLinks;

    @Transient
    private String isChecked="";

    public Teacher() {
    }

    public Teacher(int teacherId, String name, Set<Subject> subjectList, Set<Class> teacherClassList) {
        this.teacherId = teacherId;
        this.name = name;
        this.subjectList = subjectList;
        this.teacherClassList = teacherClassList;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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

    public Set<Class> getTeacherClassList() {
        return teacherClassList;
    }

    public Set<ClassSubjectTeacherLink> getClassSubjectTeacherLinks() {
        return classSubjectTeacherLinks;
    }

    public void setClassSubjectTeacherLinks(Set<ClassSubjectTeacherLink> classSubjectTeacherLinks) {
        this.classSubjectTeacherLinks = classSubjectTeacherLinks;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public void setTeacherClassList(Set<Class> teacherClassList) {
        this.teacherClassList = teacherClassList;
    }

    public void addSubject(Subject subject) {
        this.subjectList.add(subject);
        subject.getTeacherList().add(this);
    }

    public void removeSubject(Subject subject) {
        this.subjectList.remove(subject);
        subject.getTeacherList().remove(this);
    }

    public void addClass(Class aClass) {
        this.teacherClassList.add(aClass);
        aClass.getTeacherList().add(this);
    }

    public void removeClass(Class aClass) {
        this.subjectList.remove(aClass);
        aClass.getTeacherList().remove(this);
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + teacherId +
                ", name='" + name + '\'' +
                '}';
    }
}
