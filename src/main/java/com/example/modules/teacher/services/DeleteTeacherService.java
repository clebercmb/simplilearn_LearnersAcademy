package com.example.modules.teacher.services;

import com.example.modules.teacher.adapter.out.persistence.TeacherDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteTeacherService {

    private final TeacherDao teacherDao;

    public DeleteTeacherService(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void delete(int id) {
        teacherDao.delete(id);
    }
}
