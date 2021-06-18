package com.example.modules.classes.dto;

public class TeacherDto {

    private int teacherId;
    private String name;
    private String checked = "";

    public TeacherDto() {

    }

    public TeacherDto(int teacherId, String name, String checked) {
        this.teacherId = teacherId;
        this.name = name;
        this.checked = checked;
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

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
