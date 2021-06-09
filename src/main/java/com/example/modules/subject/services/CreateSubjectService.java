package com.example.modules.subject.services;

import com.example.modules.subject.adapter.out.persistence.SubjectDao;
import com.example.modules.subject.domain.Subject;
import org.springframework.stereotype.Service;

@Service
public class CreateSubjectService {

    private final SubjectDao subjectDao;

    public CreateSubjectService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public Subject execute(Subject subject) {
        return subjectDao.add(subject);
    }
}
