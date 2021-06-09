package com.example.modules.classes.services;

import com.example.modules.classes.adapter.in.web.AssignSubjectToClassCommand;
import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.classes.domain.Class;
import com.example.modules.subject.adapter.out.persistence.SubjectDao;
import com.example.modules.subject.domain.Subject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AssignSubjectToClassService {

    private final ClassDao classDao;
    private final SubjectDao subjectDao;

    public AssignSubjectToClassService(ClassDao classDao, SubjectDao subjectDao) {
        this.classDao = classDao;
        this.subjectDao = subjectDao;
    }

    public Class execute(int idClass, List<Integer> subjectIdList) {
        
        Optional<Class> hasClass = classDao.get(idClass);
        Class aClass = null;
        if ( !hasClass.isPresent() ) {
            return null;
        }
        aClass = hasClass.get();

        if(subjectIdList == null || subjectIdList.size() == 0) {
            aClass.setSubjectList(null);
            classDao.update(aClass);
            return aClass;
        }

        List<Subject> subjectList = getSubjects(subjectIdList);

        if( subjectList.size() > 0 ) {
            aClass.setSubjectList(subjectList);
            classDao.update(aClass);
        }

        return aClass;
    }

    private List<Subject> getSubjects(List<Integer> subjectIdList) {
        List<Subject> subjectList = new ArrayList<>();
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
