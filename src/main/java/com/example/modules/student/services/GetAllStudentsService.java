package com.example.modules.student.services;

import com.example.modules.student.adapter.out.persistence.StudentDao;
import com.example.modules.student.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllStudentsService {

    private StudentDao studentDao;

    public GetAllStudentsService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> execute() {
        return studentDao.get();
    }

}
