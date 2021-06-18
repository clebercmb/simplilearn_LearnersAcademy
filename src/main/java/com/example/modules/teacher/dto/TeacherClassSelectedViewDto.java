package com.example.modules.teacher.dto;

import com.example.modules.classes.domain.Class;
import com.example.modules.subject.domain.Subject;
import com.example.modules.teacher.domain.Teacher;

import java.util.Set;

public class TeacherClassSelectedViewDto extends Teacher {

    private String checked;

    public TeacherClassSelectedViewDto() {
        super();
    }

    public TeacherClassSelectedViewDto(String checked) {
        this.checked = checked;
    }

    public TeacherClassSelectedViewDto(int teacherId, String name, Set<Subject> subjectList, Set<Class> teacherClassList, String checked) {
        super(teacherId, name, subjectList, teacherClassList);
        this.checked = checked;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "TeacherClassSelectedViewDto{" +
                "checked='" + checked + '\'' +
                "super=" + super.toString()+ '\'' +
                '}';
    }
}
