package com.example.modules.classes.adapter.out.persistence;

import com.example.config.HibernateUtil;
import com.example.modules.classes.domain.Class;
import com.example.modules.subject.adapter.out.persistence.SubjectDao;
import com.example.modules.subject.domain.Subject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

        return aClasses;
    }

    @Override
    public Optional<Object> update(Class classUpdated) {
        Transaction transaction = null;
        Set<Subject> subjectList = classUpdated.getSubjectList();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();



            //Query query = session.createQuery("from Class where classId = :id");
            //query.setParameter("id", classUpdated.getClassId());

            //List<?> list = query.list();
            //if(list.size() == 0) {
            //    return Optional.empty();
            //}
            //Class newClass = (Class) list.get(0);

            Class newClass = session.load(Class.class, classUpdated.getClassId());
            newClass.getSubjectList().toString();
            newClass.setName("Class NOVA");

            //newClass.setStudentList(classUpdated.getStudentList());
            Set<Subject> subjectSet = new HashSet<>();
            for(Subject subject: classUpdated.getSubjectList()) {
                Subject sub = session.load(Subject.class, subject.getSubjectId());
                sub.toString();

                //sub.getClassList().toString();
                //sub.getClassList().add(classUpdated);
                sub.getClassList().add(newClass);
                //subjectSet.add(sub);
                newClass.getSubjectList().add(sub);
                //newClass.getSubjectList().add(sub);
            }
            //newClass.setSubjectList(subjectSet);

            //session.update(newClass);
            session.update(newClass);
            transaction.commit();
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
        } catch (Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }

    }
}
