package com.example.modules.teacher.services;

import com.example.modules.teacher.adapter.out.persistence.TeacherDao;
import com.example.modules.teacher.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTeacherService {

    private final TeacherDao teacherDao;

    public GetAllTeacherService(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public List<Teacher> execute() {
        return teacherDao.get();
    }

}
