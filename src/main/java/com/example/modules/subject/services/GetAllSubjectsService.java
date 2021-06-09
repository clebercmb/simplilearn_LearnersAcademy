package com.example.modules.subject.services;

import com.example.modules.student.adapter.out.persistence.StudentDao;
import com.example.modules.student.domain.Student;
import com.example.modules.subject.adapter.out.persistence.SubjectDao;
import com.example.modules.subject.domain.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllSubjectsService {

    private final SubjectDao subjectDao;

    public GetAllSubjectsService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public List<Subject> execute() {
        return subjectDao.get();
    }

}
