package com.example.modules.classes.services;

import com.example.modules.classes.adapter.out.persistence.ClassDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteClassService {

    private final ClassDao classDao;

    public DeleteClassService(ClassDao classDao) {
        this.classDao = classDao;
    }

    public void delete(int id) {
        classDao.delete(id);
    }
}
