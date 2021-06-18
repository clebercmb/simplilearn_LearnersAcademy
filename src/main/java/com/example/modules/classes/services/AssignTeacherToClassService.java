package com.example.modules.classes.services;

import com.example.modules.classes.adapter.in.web.AssignTeacherToClassCommand;
import com.example.modules.classes.dto.ClassDto;
import com.example.modules.classes.dto.ClassSubjectTeacherIdsDto;
import org.springframework.stereotype.Service;
import com.example.modules.classes.adapter.out.persistence.ClassDao;
import com.example.modules.classes.domain.Class;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignTeacherToClassService {

    private ClassDao classDao;

    public AssignTeacherToClassService(ClassDao classDao) {
        this.classDao = classDao;
    }

    public Class execute(AssignTeacherToClassCommand assignTeacherToClassCommand) {

        List<String> subjectTeacherIdsList = assignTeacherToClassCommand.getTeacherListId();
        int classId = assignTeacherToClassCommand.getIdClass();

        List<ClassSubjectTeacherIdsDto> teacherClassList = new ArrayList<>();
        for(String subjectTeacherId : subjectTeacherIdsList) {
            String ids[] = subjectTeacherId.split("[|]");
            int subjectId = Integer.parseInt(ids[0]);
            int teacherId = Integer.parseInt(ids[1]);
            ClassSubjectTeacherIdsDto classDto = new ClassSubjectTeacherIdsDto(classId, subjectId, teacherId);
            teacherClassList.add(classDto);
        }

        Class aClass = classDao.addTeachers(classId, teacherClassList).get();

        return aClass;

    }
}
