package com.example.modules.student.adapter.out.persistence;

import com.example.config.HibernateUtil;
import com.example.modules.classes.domain.Class;
import com.example.modules.student.domain.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StudentDaoImpl implements  StudentDao{

    public StudentDaoImpl() {
        }

    @Override
    public Student add(Student student) {
        System.out.println("$$$$$$$$$Student.add="+ student);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(student);
            System.out.println("$$$$$$$$$$$student added student="+student);
            transaction.commit();
            return student;
        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }

    @Override
    public Optional<Student> get(int id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Student where studentId = :id");
            query.setParameter("id", id);

            List<?> list = query.list();
            if(list.size() == 0) {
                return Optional.empty();
            }
            Student student = (Student) list.get(0);
            return Optional.ofNullable(student);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }

    @Override
    public List<Student> get() {
        System.out.println("$$$$$$$$$Class.get");
        List<Student> students = null;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<Student> query = session.createQuery("from Student");
            students = query.list();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

        return students;
    }

    @Override
    public Student update(Student student) {
        return null;
    }


    @Override
    public void delete(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Student where studentId = :id");
            query.setParameter("id", id);

            int result = query.executeUpdate();

            System.out.println("$$$$$$$$$Student delete.result = "+ result);

            transaction.commit();
        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }

    @Override
    public List<Student> getStudentsWithoutClass() {
        return null;
    }
}
