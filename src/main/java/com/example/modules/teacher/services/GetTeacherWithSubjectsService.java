package com.example.modules.teacher.services;

import com.example.modules.subject.adapter.out.persistence.SubjectDao;
import com.example.modules.subject.domain.Subject;
import com.example.modules.subject.dto.SubjectSelectedViewDto;
import com.example.modules.teacher.adapter.in.web.AssignTeacherToSubjectCommand;
import com.example.modules.teacher.adapter.out.persistence.TeacherDao;
import com.example.modules.teacher.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetTeacherWithSubjectsService {

    private final TeacherDao teacherDao;
    private final SubjectDao subjectDao;

    public GetTeacherWithSubjectsService(TeacherDao teacherDao, SubjectDao subjectDao) {
        this.teacherDao = teacherDao;
        this.subjectDao = subjectDao;
    }

    public AssignTeacherToSubjectCommand execute(int idClass) {
        Optional<Teacher> hasTeacher = teacherDao.get(idClass);
        List<Subject> subjectList = subjectDao.get();

        AssignTeacherToSubjectCommand assignSubjectToClassCommand = new AssignTeacherToSubjectCommand() ;

        List<SubjectSelectedViewDto> subjectDtoList = getSubjectSelectedViewDtos(subjectList);

        if(hasTeacher.isPresent()) {
            Teacher teacher = hasTeacher.get();
            assignSubjectToClassCommand.setTeacher(teacher);
            markTeacherSubjectAsChecked(subjectDtoList, teacher);
        }
        assignSubjectToClassCommand.setSubjectList(subjectDtoList);

        return assignSubjectToClassCommand;
    }

    private void markTeacherSubjectAsChecked(List<SubjectSelectedViewDto> subjectDtoList, Teacher teacher) {
        if(teacher.getSubjectList() == null) {
            return;
        }
        for(Subject subject: teacher.getSubjectList()) {
            Optional<SubjectSelectedViewDto> hasSubjectDto =  subjectDtoList.stream().filter(s->s.getId() == subject.getId()).findFirst();
            hasSubjectDto.ifPresent(subjectSelectedViewDto -> subjectSelectedViewDto.setChecked("checked"));
        }
    }

    private List<SubjectSelectedViewDto> getSubjectSelectedViewDtos(List<Subject> subjectList) {
        List<SubjectSelectedViewDto> subjectDtoList = new ArrayList<>();
        for (Subject subject : subjectList) {
            SubjectSelectedViewDto subjectDto = new SubjectSelectedViewDto();
            subjectDto.setId(subject.getId());
            subjectDto.setName(subject.getName());
            subjectDtoList.add(subjectDto);
        }
        return subjectDtoList;
    }

}
