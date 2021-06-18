package com.example.modules.classes.services;

import com.example.modules.classes.adapter.in.web.AssignTeacherToClassCommand;
import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.classes.domain.ClassSubjectTeacherLink;
import com.example.modules.classes.dto.ClassDto;
import com.example.modules.classes.dto.SubjectDto;
import com.example.modules.classes.dto.TeacherDto;
import com.example.modules.subject.domain.Subject;
import com.example.modules.teacher.domain.Teacher;
import com.mysql.cj.xdevapi.Client;
import org.springframework.stereotype.Service;
import com.example.modules.classes.domain.Class;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class GetSubjectsToAssignTeachersToClassService {

    private final ClassDao classDao;

    public GetSubjectsToAssignTeachersToClassService(ClassDao classDao) {
        this.classDao = classDao;
    }

    @Transactional
    public AssignTeacherToClassCommand execute(int idClass) {

        Class aClass = classDao.getClassWithSubjectList(idClass);

        //Set<ClassSubjectTeacherLink> classSubjectTeacherLink = classDao.getAssignedTeachers(idClass);


        ClassDto classDto = new ClassDto();
        classDto.setClassId(aClass.getClassId());
        classDto.setName(aClass.getName());
        classDto.setSubjects(new HashSet<SubjectDto>());
        for(Subject subject : aClass.getSubjectList()) {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setSubjectId(subject.getSubjectId());
            subjectDto.setName(subject.getName());
            subjectDto.setTeachers(new HashSet<TeacherDto>());
            classDto.addSubject(subjectDto);
            for(Teacher teacher : subject.getTeacherList()) {
                TeacherDto teacherDto = new TeacherDto();
                teacherDto.setTeacherId(teacher.getTeacherId());
                teacherDto.setName(teacher.getName());
                for(ClassSubjectTeacherLink classSubjectTeacherLink: aClass.getClassSubjectTeacherLinks()) {
                    if(classSubjectTeacherLink.getaClass().getClassId() == idClass &&
                        classSubjectTeacherLink.getSubject().getSubjectId() == subject.getSubjectId() &&
                        classSubjectTeacherLink.getTeacher().getTeacherId() == teacher.getTeacherId()) {
                        teacherDto.setChecked("checked");
                    }
                }
                subjectDto.addTeacher(teacherDto);
            }

        }

        AssignTeacherToClassCommand assignTeacherToClassCommand = new AssignTeacherToClassCommand();
        assignTeacherToClassCommand.setaClass(classDto);

        return  assignTeacherToClassCommand;
    }
}
