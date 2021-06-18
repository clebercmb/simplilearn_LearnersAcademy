package com.example.modules.classes.services;

import com.example.modules.classes.adapter.in.web.AssignStudentToClassCommand;
import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.classes.domain.Class;
import com.example.modules.student.adapter.out.persistence.StudentDao;
import com.example.modules.student.domain.Student;
import com.example.modules.student.dto.StudentSelectedViewDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetClassWithStudentsService {

    private final ClassDao classDao;
    private final StudentDao studentDao;

    public GetClassWithStudentsService(ClassDao classDao, StudentDao studentDao) {
        this.classDao = classDao;
        this.studentDao = studentDao;
    }

    public AssignStudentToClassCommand execute(int idClass) {
        Optional<Class> hasClass = classDao.get(idClass);
        List<Student> studentList = studentDao.get();

        AssignStudentToClassCommand assignStudentToClassCommand = new AssignStudentToClassCommand() ;

        List<Student> allAssignedStudentExcludingIdClass = getAllAssignedStudentsExcludingIdClass(idClass);

        List<Student> assignableStudents = filterAssignableStudents(allAssignedStudentExcludingIdClass, studentList);
        List<StudentSelectedViewDto> studentDtoList = getStudentSelectedViewDtos(assignableStudents);


        if(hasClass.isPresent()) {
            Class aClass = hasClass.get();
            assignStudentToClassCommand.setaClass(aClass);
            markClassStudentAsChecked(studentDtoList, aClass);
        }
        assignStudentToClassCommand.setStudentList(studentDtoList);

        return assignStudentToClassCommand;
    }

    private void markClassStudentAsChecked(List<StudentSelectedViewDto> studentDtoList, Class aClass) {
        if(aClass.getStudentList() == null) {
            return;
        }
        for(Student student: aClass.getStudentList()) {
            Optional<StudentSelectedViewDto> hasStudentDto =  studentDtoList.stream().filter(s->s.getId() == student.getStudentId()).findFirst();
            hasStudentDto.ifPresent(studentSelectedViewDto -> studentSelectedViewDto.setChecked("checked"));
        }
    }

    private List<StudentSelectedViewDto> getStudentSelectedViewDtos(List<Student> studentList) {
        List<StudentSelectedViewDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList) {
            StudentSelectedViewDto studentDto = new StudentSelectedViewDto();
            studentDto.setId(student.getStudentId());
            studentDto.setName(student.getName());
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }

    private List<Student> getAllAssignedStudentsExcludingIdClass(int idClass) {

        List<Class> classList = classDao.getAllAssignedStudentsExcludingIdClass(idClass);

        List<Student> result = new ArrayList<>();
        for(Class aClass : classList) {
            if( aClass.getClassId() == idClass || aClass.getStudentList() == null) {
                continue;
            }
            aClass.getStudentList().toString();
            result.addAll(aClass.getStudentList());
        }
        return result;
    }

    private List<Student> filterAssignableStudents(List<Student> assignedStudentList, List<Student> studentList) {
        List<Student> result = new ArrayList<>();

        for(Student student : studentList) {

            if( assignedStudentList.stream().anyMatch(s->s.getStudentId() == student.getStudentId()) ) {
                continue;
            }
            result.add(student);
        }

        return result;
    }

}
