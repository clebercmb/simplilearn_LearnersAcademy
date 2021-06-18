package com.example.modules.classes.services;

import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.classes.domain.Class;
import com.example.modules.classes.domain.ClassSubjectTeacherLink;
import com.example.modules.classes.dto.ClassDto;
import com.example.modules.classes.dto.StudentDto;
import com.example.modules.classes.dto.SubjectDto;
import com.example.modules.classes.dto.TeacherDto;
import com.example.modules.student.domain.Student;
import com.example.modules.subject.domain.Subject;
import com.example.modules.teacher.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ClassReportService {

    private ClassDao classDao;

    public ClassReportService(ClassDao classDao) {
        this.classDao = classDao;
    }

    public ClassDto execute(int classId) {

        Class aClass = classDao.getClassWithSubjectList(classId);
        ClassDto classDto = new ClassDto();
        classDto.setClassId(aClass.getClassId());
        classDto.setName(aClass.getName());
        classDto.setSubjects(new HashSet<SubjectDto>());
        for(Subject subject : aClass.getSubjectList()) {

            boolean hasSubject = false;
            for(ClassSubjectTeacherLink classSubjectTeacherLink: aClass.getClassSubjectTeacherLinks()) {
                if(classSubjectTeacherLink.getaClass().getClassId() == classId &&
                        classSubjectTeacherLink.getSubject().getSubjectId() == subject.getSubjectId() ) {
                    hasSubject = true;
                    break;
                }
            }
            if(!hasSubject) {
                continue;
            }

            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setSubjectId(subject.getSubjectId());
            subjectDto.setName(subject.getName());
            subjectDto.setTeachers(new HashSet<TeacherDto>());
            classDto.addSubject(subjectDto);
            for(Teacher teacher : subject.getTeacherList()) {

                for(ClassSubjectTeacherLink classSubjectTeacherLink: aClass.getClassSubjectTeacherLinks()) {
                    if(classSubjectTeacherLink.getaClass().getClassId() == classId &&
                            classSubjectTeacherLink.getSubject().getSubjectId() == subject.getSubjectId() &&
                            classSubjectTeacherLink.getTeacher().getTeacherId() == teacher.getTeacherId()) {

                        TeacherDto teacherDto = new TeacherDto();
                        teacherDto.setTeacherId(teacher.getTeacherId());
                        teacherDto.setName(teacher.getName());
                        subjectDto.addTeacher(teacherDto);
                    }
                }

            }

        }

        for(Student student : aClass.getStudentList()) {
            StudentDto studentDto = new StudentDto();
            studentDto.setStudentId(student.getStudentId());
            studentDto.setName(student.getName());
            classDto.addStudent(studentDto);
        }

        return classDto;
    }
}
