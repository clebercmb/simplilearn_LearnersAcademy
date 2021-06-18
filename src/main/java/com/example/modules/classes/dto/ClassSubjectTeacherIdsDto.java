package com.example.modules.classes.dto;

public class ClassSubjectTeacherIdsDto {

    private int classId;
    private int subjectId;
    private int teacherId;

    public ClassSubjectTeacherIdsDto(int classId, int subjectId, int teacherId) {
        this.classId = classId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "ClassSubjectTeacherIdsDto{" +
                "classId=" + classId +
                ", subjectId=" + subjectId +
                ", teacherId=" + teacherId +
                '}';
    }
}
