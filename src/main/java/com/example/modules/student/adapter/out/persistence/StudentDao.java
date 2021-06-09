package com.example.modules.student.adapter.out.persistence;

import com.example.modules.student.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    public Student add(Student student);

    public Optional<Student> get(int id);

    public List<Student> get();

    public Student update(Student student);

    public void delete(int id);

    public List<Student> getStudentsWithoutClass();



}
