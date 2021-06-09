package com.example.modules.subject.adapter.out.persistence;

import com.example.modules.subject.domain.Subject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SubjectDaoImpl implements SubjectDao {

    private static List<Subject> subjects = null;
    private static int index = 0;

    public SubjectDaoImpl() {
        if(subjects == null) {
            subjects = new ArrayList<>();
        }
    }

    @Override
    public Subject add(Subject subject) {
        subject.setId(index+1);
        index ++;
        subjects.add(subject);
        return subject;
    }

    @Override
    public Optional<Subject> get(int id) {

        return subjects.stream().filter(s -> s.getId() == id).findFirst();
    }

    @Override
    public List<Subject> get() {

        return subjects;
    }

    @Override
    public Subject update(Subject subjectUpdated) {
        Optional<Subject> hasStudent = subjects.stream().filter(s->s.getId() == subjectUpdated.getId()).findFirst();
        if (hasStudent.isPresent()) {
            Subject student = hasStudent.get();
            student.setName(subjectUpdated.getName());
        }
        return null;
    }

    @Override
    public void delete(int id) {

        subjects.removeIf(s->s.getId() == id);

    }
}
