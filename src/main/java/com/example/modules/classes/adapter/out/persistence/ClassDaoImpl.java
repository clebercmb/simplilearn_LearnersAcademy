package com.example.modules.classes.adapter.out.persistence;

import com.example.config.HibernateUtil;
import com.example.modules.classes.domain.Class;
import com.example.modules.classes.domain.ClassSubjectTeacherLink;
import com.example.modules.classes.dto.ClassSubjectTeacherIdsDto;
import com.example.modules.student.domain.Student;
import com.example.modules.classes.dto.ClassDto;
import com.example.modules.subject.adapter.out.persistence.SubjectDao;
import com.example.modules.subject.domain.Subject;
import com.example.modules.teacher.domain.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
public class ClassDaoImpl implements ClassDao {

    @Autowired
    private SubjectDao subjectDao;

    public ClassDaoImpl() {

    }

    public ClassDaoImpl(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public void setSubjectDao(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public Class add(Class aClass) {

        System.out.println("$$$$$$$$$Class.add");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(aClass);

            System.out.println("$$$$$$$$$Class="+aClass);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

        return aClass;
    }

    @Override
    public Optional<Class> get(int id) {

        System.out.println("$$$$$$$$$Class.get(id)");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Class where classId = :id");
            query.setParameter("id", id);

            List<?> list = query.list();
            if(list.size() == 0) {
                return Optional.empty();
            }
            Class aClass = (Class) list.get(0);
            aClass.getSubjectList().toString();
            aClass.getStudentList().toString();
      //      List<Subject> subjectList = aClass.getSubjectList();
      //      System.out.println(">>>>>>subjectList.size="+subjectList.size());
            transaction.commit();
            session.close();
            return Optional.ofNullable(aClass);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }

    @Override
    public List<Class> get() {

        System.out.println("$$$$$$$$$Class.get");
        List<Class> aClasses = null;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<Class> query = session.createQuery("from Class");
            aClasses = query.list();
            session.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

        return aClasses;
    }

    @Override
    public Optional<Object> addSubjects(Class classUpdated) {
        Transaction transaction = null;
        Set<Subject> subjectList = classUpdated.getSubjectList();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Class newClass = session.load(Class.class, classUpdated.getClassId());
            newClass.getSubjectList().toString();
            int size = newClass.getSubjectList().size();

            for(Subject subject : new ArrayList<Subject>(newClass.getSubjectList())) {
                subject.getClassList().toString();
                int size5=subject.getClassList().size();
                newClass.removeSubject(subject);
                subject.getClassList().remove(newClass);
                int size6=subject.getClassList().size();
                session.update(subject);
            }

            for(Subject subject: classUpdated.getSubjectList()) {
                Subject sub = session.load(Subject.class, subject.getSubjectId());

                sub.toString();
                sub.getClassList().add(newClass);
                newClass.getSubjectList().add(sub);
            }

            int size3 = newClass.getSubjectList().size();

            session.persist(newClass);
            transaction.commit();
            session.close();
            return Optional.of(newClass);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
        
    }

    @Override
    public Optional<Object> addStudents(Class classUpdated) {
        Transaction transaction = null;
        Set<Subject> subjectList = classUpdated.getSubjectList();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Class newClass = session.load(Class.class, classUpdated.getClassId());
            newClass.getStudentList().toString();
            int size = newClass.getStudentList().size();

            //newClass.setStudentList(classUpdated.getStudentList());
            //int size2 = newClass.getStudentList().size();


            List<Student> studentList = new ArrayList<>(classUpdated.getStudentList());
            List<Student> newStudentList = new ArrayList<>();
            for(Student student: studentList) {
                Student s = session.load(Student.class, student.getStudentId());
                newStudentList.add(s);
                //newClass.getStudentList().add(s);
            }
            newClass.setStudentList(newStudentList);
            session.persist(newClass);
            transaction.commit();
            session.close();
            return Optional.of(newClass);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }

    @Override
    public void delete(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Class where classId = :id");
            query.setParameter("id", id);

            int result = query.executeUpdate();

            System.out.println("$$$$$$$$$Class delete.result = "+ result);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }


    @Override
    @Transactional
    public List<Class> getAllAssignedStudentsExcludingIdClass(int id) {
        System.out.println("$$$$$$$$$Class.getAllAssignedStudentsExcludingIdClass");
        List<Class> aClasses = null;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Class> query = session.createQuery("SELECT a FROM Class a LEFT JOIN FETCH a.studentList where a.classId <> :id", Class.class);
            query.setParameter("id", id);
            aClasses = query.list();

            //aClasses.forEach(c -> c.getStudentList());
            transaction.commit();
            session.close();

   //         HibernateUtil.shutDown();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

        return aClasses;
    }


    @Override
    @Transactional
    public Class getClassWithSubjectList(int id) {
        System.out.println("$$$$$$$$$Class.getClassWithSubjectList");
        Class aClass = null;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Class> query = session.createQuery(
            "SELECT a FROM Class as a " +
                "where a.classId = :id", Class.class);
            query.setParameter("id", id);
            aClass = query.stream().findFirst().get();



            aClass.getClassSubjectTeacherLinks().toString();
            //aClasses.forEach(c -> c.getStudentList());
            transaction.commit();
            session.close();

            //         HibernateUtil.shutDown();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

        return aClass;
    }

    @Override
    public Optional<Class> addTeachers(int classId, List<ClassSubjectTeacherIdsDto> classDtoList) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Class> query = session.createQuery("DELETE FROM ClassSubjectTeacherLink where aClass.classId = :id");
            query.setParameter("id",classId);
            query.executeUpdate();

            Class newClass = session.load(Class.class, classId);
            newClass.getStudentList().toString();
            int size = newClass.getStudentList().size();

            Set<ClassSubjectTeacherLink> newTeacherList = new HashSet<>();
            for(ClassSubjectTeacherIdsDto teacher: classDtoList) {
                ClassSubjectTeacherLink newTeacher = new ClassSubjectTeacherLink();
                Subject subjectClass = session.load(Subject.class, teacher.getSubjectId());
                Teacher teacherClass = session.load(Teacher.class, teacher.getTeacherId());
                newTeacher.setaClass(newClass);
                newTeacher.setSubject(subjectClass);
                newTeacher.setTeacher(teacherClass);

                newTeacherList.add(newTeacher);
            }
            newClass.setClassSubjectTeacherLinks(newTeacherList);
            session.persist(newClass);
            transaction.commit();
            session.close();
            return Optional.of(newClass);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }

    @Override
    public Set<ClassSubjectTeacherLink> getAssignedTeachers(int classId) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<ClassSubjectTeacherLink> query = session.createQuery("FROM ClassSubjectTeacherLink where aClass.classId = :id");
            query.setParameter("id",classId);
            List<ClassSubjectTeacherLink> teacherClassList = query.list();
            Set<ClassSubjectTeacherLink> teacherClassSet = new HashSet<>();
            teacherClassSet.addAll(teacherClassList);


            transaction.commit();
            session.close();
            return teacherClassSet;

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }

    @Override
    public Class getClassAssignedTeacherList(int idClass) {
        System.out.println("$$$$$$$$$Class.getClassAssignedTeacherList");
        Class aClass = null;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Class> query = session.createQuery("SELECT a FROM Class a LEFT JOIN FETCH a.classSubjectTeacherLinks where a.classId = :id", Class.class);
            query.setParameter("id", idClass);
            aClass = query.stream().findFirst().get();

            //aClasses.forEach(c -> c.getStudentList());
            transaction.commit();
            session.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

        return aClass;
    }


}
