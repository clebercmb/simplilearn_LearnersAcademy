package com.example.modules.teacher.adapter.out.persistence;


import com.example.modules.teacher.domain.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherDao {

    public Teacher add(Teacher student);

    public Optional<Teacher> get(int id);

    public List<Teacher> get();

    public Teacher update(Teacher student);

    public void delete(int id);

}
