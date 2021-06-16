package com.example.modules.classes.services;

import com.example.modules.classes.adapter.in.web.AssignSubjectToClassCommand;
import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.subject.adapter.out.persistence.SubjectDao;
import com.example.modules.subject.domain.Subject;
import com.example.modules.subject.dto.SubjectSelectedViewDto;
import org.springframework.stereotype.Service;

import com.example.modules.classes.domain.Class;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetClassWithSubjectsService {

    private final ClassDao classDao;
    private final SubjectDao subjectDao;

    public GetClassWithSubjectsService(ClassDao classDao, SubjectDao subjectDao) {
        this.classDao = classDao;
        this.subjectDao = subjectDao;
    }

    public AssignSubjectToClassCommand execute(int idClass) {
        Optional<Class> hasClass = classDao.get(idClass);
        List<Subject> subjectList = subjectDao.get();

        AssignSubjectToClassCommand assignSubjectToClassCommand = new AssignSubjectToClassCommand() ;

        List<SubjectSelectedViewDto> subjectDtoList = getSubjectSelectedViewDtos(subjectList);

        if(hasClass.isPresent()) {
            Class aClass = hasClass.get();
            assignSubjectToClassCommand.setaClass(aClass);
            markClassSubjectAsChecked(subjectDtoList, aClass);
        }
        assignSubjectToClassCommand.setSubjectList(subjectDtoList);

        return assignSubjectToClassCommand;
    }

    private void markClassSubjectAsChecked(List<SubjectSelectedViewDto> subjectDtoList, Class aClass) {
        if(aClass.getSubjectList() == null) {
            return;
        }
        for(Subject subject: aClass.getSubjectList()) {
            Optional<SubjectSelectedViewDto> hasSubjectDto =  subjectDtoList.stream().filter(s->s.getId() == subject.getSubjectId()).findFirst();
            hasSubjectDto.ifPresent(subjectSelectedViewDto -> subjectSelectedViewDto.setChecked("checked"));
        }
    }

    private List<SubjectSelectedViewDto> getSubjectSelectedViewDtos(List<Subject> subjectList) {
        List<SubjectSelectedViewDto> subjectDtoList = new ArrayList<>();
        for (Subject subject : subjectList) {
            SubjectSelectedViewDto subjectDto = new SubjectSelectedViewDto();
            subjectDto.setId(subject.getSubjectId());
            subjectDto.setName(subject.getName());
            subjectDtoList.add(subjectDto);
        }
        return subjectDtoList;
    }
}
