package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Student;

public class CreateStudentDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").
                                        addAnnotatedClass(Student.class).buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();

        try {
            Student theStudent = new Student("Bob", "Doe", "example@ukr.net");
            currentSession.beginTransaction();
            currentSession.save(theStudent);
            currentSession.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }

}
