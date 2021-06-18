package com.example.modules.classes.domain;

import com.example.modules.subject.domain.Subject;
import com.example.modules.teacher.domain.Teacher;

import javax.persistence.*;

@Entity
@Table(name="class_subject_teacher_link")
public class ClassSubjectTeacherLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id")
    private Class aClass;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public ClassSubjectTeacherLink() {
    }

    public ClassSubjectTeacherLink(int id, Class aClass, Subject subject, Teacher teacher) {
        this.id = id;
        this.aClass = aClass;
        this.subject = subject;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "ClassSubjectTeacherLink{" +
                "id=" + id +
                ", aClass=" + aClass +
                ", subject=" + subject +
                ", teacher=" + teacher +
                '}';
    }
}
