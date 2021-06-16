package com.example.modules.subject.adapter.out.persistence;

import com.example.config.HibernateUtil;
import com.example.modules.subject.domain.Subject;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class SubjectDaoImpl implements SubjectDao {


    public SubjectDaoImpl() {

    }

    @Override
    public Subject add(Subject subject) {
        System.out.println("Subject.add="+ subject);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(subject);
            System.out.println("subject added subject="+subject);
            transaction.commit();
            return subject;
        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public Optional<Subject> get(int id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Subject where  subjectId = :id");
            query.setParameter("id", id);

            List<?> list = query.list();
            if(list.size() == 0) {
                return Optional.empty();
            }
            Subject subject = (Subject) list.get(0);
            return Optional.ofNullable(subject);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public List<Subject> get() {

        System.out.println("$$$$$$$$$Class.get");
        List<Subject> subjects = null;

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query<Subject> query = session.createQuery("from Subject");
            subjects = query.list();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

        return subjects;
    }

    @Override
    public Subject update(Subject subjectUpdated) {

        return null;
    }

    @Override
    public void delete(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Subject where subjectId = :id");
            query.setParameter("id", id);

            int result = query.executeUpdate();

            System.out.println("$$$$$$$$$Subject delete.result = "+ result);

            transaction.commit();
        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }
}
