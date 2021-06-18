package com.example.modules.classes.adapter.out.persistence;


import com.example.modules.classes.domain.Class;
import com.example.modules.classes.domain.ClassSubjectTeacherLink;
import com.example.modules.classes.dto.TeacherClassDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClassDao {

    public Class add(Class student);

    public Optional<Class> get(int id);

    public List<Class> get();

    public Optional<Object> addSubjects(Class aclass);

    public Optional<Object> addStudents(Class aclass);

    public void delete(int id);

    public List<Class> getAllAssignedStudentsExcludingIdClass(int idClass);

    public Class getClassWithSubjectList(int idClass);

    public Optional<Class> addTeachers(int classId, List<TeacherClassDto> teacherList);

    public Set<ClassSubjectTeacherLink> getAssignedTeachers(int classId);

    public Class getClassAssignedTeacherList(int idClass);


}
