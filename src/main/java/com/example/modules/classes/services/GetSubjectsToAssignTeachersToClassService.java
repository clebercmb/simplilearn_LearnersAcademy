package com.example.modules.classes.services;

import com.example.modules.classes.adapter.in.web.AssignTeacherToClassCommand;
import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.classes.domain.ClassSubjectTeacherLink;
import com.example.modules.subject.domain.Subject;
import com.example.modules.teacher.domain.Teacher;
import org.springframework.stereotype.Service;
import com.example.modules.classes.domain.Class;

import java.util.Set;

@Service
public class GetSubjectsToAssignTeachersToClassService {

    private final ClassDao classDao;

    public GetSubjectsToAssignTeachersToClassService(ClassDao classDao) {
        this.classDao = classDao;
    }

    public AssignTeacherToClassCommand execute(int idClass) {

        Class aClass = classDao.getClassWithSubjectList(idClass);

        //Set<ClassSubjectTeacherLink> classSubjectTeacherLink = classDao.getAssignedTeachers(idClass);

        AssignTeacherToClassCommand assignTeacherToClassCommand = new AssignTeacherToClassCommand();
        assignTeacherToClassCommand.setaClass(aClass);

        for(Subject subject : aClass.getSubjectList()) {

            for(Teacher teacher : subject.getTeacherList()) {

                boolean hasFound = aClass.getClassSubjectTeacherLinks()
                    .stream()
                    .anyMatch(t->t.getSubject().getSubjectId() == subject.getSubjectId()
                            && t.getTeacher().getTeacherId() == teacher.getTeacherId());
                if( hasFound ) {
                    teacher.setIsChecked("checked");
                }
            }

        }

        return  assignTeacherToClassCommand;
    }
}
