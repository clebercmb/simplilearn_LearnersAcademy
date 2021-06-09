package com.example.modules.teacher.services;

import com.example.modules.teacher.adapter.out.persistence.TeacherDao;
import com.example.modules.teacher.domain.Teacher;
import org.springframework.stereotype.Service;

@Service
public class CreateTeacherService {

    private final TeacherDao teacherDao;

    public CreateTeacherService(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public Teacher execute(Teacher teacher) {
        return teacherDao.add(teacher);
    }
}
