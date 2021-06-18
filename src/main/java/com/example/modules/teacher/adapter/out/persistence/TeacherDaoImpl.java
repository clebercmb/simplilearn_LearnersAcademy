package com.example.modules.teacher.adapter.out.persistence;

import com.example.config.HibernateUtil;
import com.example.modules.classes.domain.Class;
import com.example.modules.subject.domain.Subject;
import com.example.modules.teacher.domain.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TeacherDaoImpl implements TeacherDao {


    public TeacherDaoImpl() {

    }

    @Override
    public Teacher add(Teacher teacher) {
        System.out.println("Teacher.add="+ teacher);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(teacher);
            System.out.println("teacher added teacher="+teacher);
            transaction.commit();
            session.close();
            return teacher;
        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }

    @Override
    public Optional<Teacher> get(int id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Teacher where  teacherId = :id");
            query.setParameter("id", id);

            List<?> list = query.list();
            if(list.size() == 0) {
                return Optional.empty();
            }
            Teacher teacher = (Teacher) list.get(0);
            session.close();
            return Optional.ofNullable(teacher);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public List<Teacher> get() {
        System.out.println("$$$$$$$$$Class.get");
        List<Teacher> teachers = null;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<Teacher> query = session.createQuery("from Teacher");
            teachers = query.list();
            session.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

        return teachers;
    }

    @Override
    public Optional<Object> update(Teacher teacherUpdated) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            //Query query = session.createQuery("from Teacher where teacherId = :id");
            //query.setParameter("id", teacherUpdated.getTeacherId());

            //List<?> list = query.list();
            //if(list.size() == 0) {
            //    return Optional.empty();
            //}
            Teacher newTeacher = session.load(Teacher.class, teacherUpdated.getTeacherId()); //(Teacher) list.get(0);
            newTeacher.setName(teacherUpdated.getName());

            for(Subject subject : new ArrayList<Subject>(newTeacher.getSubjectList())) {
                Subject s =  session.load(Subject.class, subject.getSubjectId());
                newTeacher.removeSubject(s);
                session.update(s);
            }

            //wTeacher.setSubjectList(teacherUpdated.getSubjectList());
            for(Subject subject : new ArrayList<Subject>(teacherUpdated.getSubjectList())) {
                Subject s =  session.load(Subject.class, subject.getSubjectId());
                newTeacher.addSubject(s);
                session.update(s);
            }
            session.update(newTeacher);
            transaction.commit();
            session.close();
            return Optional.ofNullable(newTeacher);

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

            Query query = session.createQuery("delete from Teacher where teacherId = :id");
            query.setParameter("id", id);

            int result = query.executeUpdate();

            System.out.println("$$$$$$$$$Student delete.result = "+ result);

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
}
