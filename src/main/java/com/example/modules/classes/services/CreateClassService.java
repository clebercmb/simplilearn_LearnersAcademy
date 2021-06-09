package com.example.modules.classes.services;

import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.classes.domain.Class;
import org.springframework.stereotype.Service;

@Service
public class CreateClassService {

    private final ClassDao classDao;

    public CreateClassService(ClassDao classDao) {
        this.classDao = classDao;
    }

    public Class execute(Class aClass) {
        return classDao.add(aClass);
    }
}
