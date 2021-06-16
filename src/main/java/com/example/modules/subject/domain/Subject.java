package com.example.modules.subject.domain;

import com.example.modules.teacher.domain.Teacher;
import com.example.modules.classes.domain.Class;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subject_id")
    private int subjectId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subject_class", joinColumns = { @JoinColumn(name = "subject_id") }, inverseJoinColumns = { @JoinColumn(name = "class_id") })
    private Set<Class> classList = new HashSet<>();

    @ManyToMany(mappedBy="subjectList")
    private List<Teacher> teacherList;

    public Subject() {
    }

    public Subject(String name, Set<Class> classList, List<Teacher> teacherList) {
        this.name = name;
        this.classList = classList;
        this.teacherList = teacherList;
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

    public Set<Class> getClassList() {
        return classList;
    }

    public void setClassList(Set<Class> classList) {
        this.classList = classList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + subjectId +
                ", name='" + name + '\'' +
                '}';
    }
}
