package com.example.modules.student.services;

import com.example.modules.student.adapter.out.persistence.StudentDao;
import com.example.modules.student.domain.Student;
import org.springframework.stereotype.Service;

@Service
public class CreateStudentService {

    private StudentDao studentDao;

    public CreateStudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Student execute(Student student) {
        Student newStudent = studentDao.add(student);

        return newStudent;
    }

}
