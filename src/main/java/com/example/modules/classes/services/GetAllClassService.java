package com.example.modules.classes.services;

import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.classes.domain.Class;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllClassService {

    private final ClassDao classDao;

    public GetAllClassService(ClassDao classDao) {
        this.classDao = classDao;
    }

    public List<Class> execute() {
        return classDao.get();
    }

}
