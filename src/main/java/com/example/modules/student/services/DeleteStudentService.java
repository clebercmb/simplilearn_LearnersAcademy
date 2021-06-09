package com.example.modules.student.services;

import com.example.modules.student.adapter.out.persistence.StudentDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteStudentService {

    private StudentDao studentDao;

    public DeleteStudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void delete(int id) {
        studentDao.delete(id);
    }
}
