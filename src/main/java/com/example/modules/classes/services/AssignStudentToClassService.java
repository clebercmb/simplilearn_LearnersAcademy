package com.example.modules.classes.services;

import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.classes.domain.Class;
import com.example.modules.student.adapter.out.persistence.StudentDao;
import com.example.modules.student.domain.Student;
import com.example.modules.subject.domain.Subject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AssignStudentToClassService {

    private final ClassDao classDao;
    private final StudentDao studentDao;

    public AssignStudentToClassService(ClassDao classDao, StudentDao studentDao) {
        this.classDao = classDao;
        this.studentDao = studentDao;
    }

    public Class execute(int idClass, List<Integer> studentIdList) {
        
        Optional<Class> hasClass = classDao.get(idClass);
        Class aClass = null;
        if ( !hasClass.isPresent() ) {
            return null;
        }
        aClass = hasClass.get();

        if(studentIdList == null || studentIdList.size() == 0) {
            aClass.setStudentList(null);
            classDao.update(aClass);
            return aClass;
        }

        List<Student> studentList = getStudents(studentIdList);

        if( studentList.size() > 0 ) {
            aClass.setStudentList(studentList);
            classDao.update(aClass);
        }

        return aClass;
    }

    private List<Student> getStudents(List<Integer> studentIdList) {
        List<Student> studentList = new ArrayList<>();
        for (Integer studentId : studentIdList) {
            Optional<Student> hasStudent = studentDao.get(studentId);
            if( !hasStudent.isPresent()) {
                continue;
            }
            Student student = hasStudent.get();
            studentList.add(student);
        }
        return studentList;
    }

}
