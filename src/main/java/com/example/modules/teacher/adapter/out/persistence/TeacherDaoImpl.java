package com.example.modules.teacher.adapter.out.persistence;

import com.example.modules.teacher.domain.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TeacherDaoImpl implements TeacherDao {

    private static List<Teacher> teachers = null;
    private static int index = 0;

    public TeacherDaoImpl() {
        if(teachers == null) {
            teachers = new ArrayList<>();
        }
    }

    @Override
    public Teacher add(Teacher teacher) {
        teacher.setId(index+1);
        index++;
        teachers.add(teacher);
        return teacher;
    }

    @Override
    public Optional<Teacher> get(int id) {

        return teachers.stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public List<Teacher> get() {

        return teachers;
    }

    @Override
    public Teacher update(Teacher teacherUpdated) {
        Optional<Teacher> hasTeacher = teachers.stream().filter(s->s.getId() == teacherUpdated.getId()).findFirst();
        if (hasTeacher.isPresent()) {
            Teacher teacher = hasTeacher.get();
            teacher.setName(teacherUpdated.getName());
        }
        return null;
    }

    @Override
    public void delete(int id) {

        teachers.removeIf(s->s.getId() == id);

    }
}
