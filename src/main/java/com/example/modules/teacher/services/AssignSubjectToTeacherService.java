package com.example.modules.teacher.services;

import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.classes.domain.Class;
import com.example.modules.subject.adapter.out.persistence.SubjectDao;
import com.example.modules.subject.domain.Subject;
import com.example.modules.teacher.adapter.out.persistence.TeacherDao;
import com.example.modules.teacher.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AssignSubjectToTeacherService {

    private final TeacherDao teacherDao;
    private final SubjectDao subjectDao;

    public AssignSubjectToTeacherService(TeacherDao teacherDao, SubjectDao subjectDao) {
        this.teacherDao = teacherDao;
        this.subjectDao = subjectDao;
    }

    public Teacher execute(int idClass, List<Integer> subjectIdList) {
        
        Optional<Teacher> hasTeacher = teacherDao.get(idClass);
        Teacher teacher = null;
        if ( !hasTeacher.isPresent() ) {
            return null;
        }
        teacher = hasTeacher.get();

        if(subjectIdList == null || subjectIdList.size() == 0) {
            teacher.setSubjectList(new HashSet<>());
            teacherDao.update(teacher);
            return teacher;
        }

        Set<Subject> subjectList = getSubjects(subjectIdList);

        if( subjectList.size() > 0 ) {
            teacher.setSubjectList(subjectList);
            teacherDao.update(teacher);
        }

        return teacher;
    }

    private Set<Subject> getSubjects(List<Integer> subjectIdList) {
        Set<Subject> subjectList = new HashSet<>();
        for (Integer subjectId : subjectIdList) {
            Optional<Subject> hasSubject = subjectDao.get(subjectId);
            if( !hasSubject.isPresent()) {
                continue;
            }
            Subject subject = hasSubject.get();
            subjectList.add(subject);
        }
        return subjectList;
    }

}
