package com.example.modules.classes.dto;

public class TeacherClassDto {

    private int idClass;
    private int idSubject;
    private int idTeacher;


    public TeacherClassDto(int idClass, int idSubject, int idTeacher) {
        this.idClass = idClass;
        this.idSubject = idSubject;
        this.idTeacher = idTeacher;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    @Override
    public String toString() {
        return "TeacherClassDto{" +
                "idClass=" + idClass +
                ", subject=" + idSubject +
                ", teacher=" + idTeacher +
                '}';
    }
}
