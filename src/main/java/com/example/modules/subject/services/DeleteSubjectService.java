package com.example.modules.subject.services;

import com.example.modules.student.adapter.out.persistence.StudentDao;
import com.example.modules.subject.adapter.out.persistence.SubjectDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteSubjectService {

    private final SubjectDao subjectDao;

    public DeleteSubjectService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public void delete(int id) {
        subjectDao.delete(id);
    }
}
