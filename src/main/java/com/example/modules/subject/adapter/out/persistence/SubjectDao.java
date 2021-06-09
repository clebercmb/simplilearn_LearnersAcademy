package com.example.modules.subject.adapter.out.persistence;


import com.example.modules.subject.domain.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectDao {

    public Subject add(Subject student);

    public Optional<Subject> get(int id);

    public List<Subject> get();

    public Subject update(Subject student);

    public void delete(int id);

}
