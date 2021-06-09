package com.example.modules.student.adapter.out.persistence;

import com.example.modules.student.domain.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentDaoImpl implements  StudentDao{

    private static List<Student> students = null;
    private static int index = 0;

    public StudentDaoImpl() {
        if(students == null) {
            students = new ArrayList<>();
        }
    }

    @Override
    public Student add(Student student) {
        student.setId(index+1);
        index ++;
        students.add(student);
        return student;
    }

    @Override
    public Optional<Student> get(int id) {

        Optional<Student> student =  students.stream().filter(s -> s.getId() == id).findFirst();
        return student;
    }

    @Override
    public List<Student> get() {

        return students;
    }

    @Override
    public Student update(Student studentUpdated) {
        Optional<Student> hasStudent = students.stream().filter(s->s.getId() == studentUpdated.getId()).findFirst();
        if (hasStudent.isPresent()) {
            Student student = hasStudent.get();
            student.setName(studentUpdated.getName());
            student.setEmail(studentUpdated.getEmail());
        }


        return null;
    }

    @Override
    public void delete(int id) {
        students.removeIf(s->s.getId()==id);
    }
}
