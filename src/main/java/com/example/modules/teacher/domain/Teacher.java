package com.example.modules.teacher.domain;

import com.example.modules.subject.domain.Subject;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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
    private List<Subject> subjectList;


    public Teacher() {
    }

    public Teacher(String name, List<Subject> subjectList) {
        this.name = name;
        this.subjectList = subjectList;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int id) {
        this.teacherId = id;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + teacherId +
                ", name='" + name + '\'' +
                '}';
    }
}
