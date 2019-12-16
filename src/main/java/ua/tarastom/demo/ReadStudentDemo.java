package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Student;

public class ReadStudentDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").
                                        addAnnotatedClass(Student.class).buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();

        try {
            //сохраняем в базу объект
            Student theStudent = new Student("Daffy", "Duck", "daffy@ukr.net");
            currentSession.beginTransaction();
            currentSession.save(theStudent);
            currentSession.getTransaction().commit();

            //достаем из БД объект
            System.out.println("Saved student. Generated ID: " + theStudent.getId());
            currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            Student myStudent = currentSession.get(Student.class, theStudent.getId());
            currentSession.getTransaction().commit();
            System.out.println(myStudent);
        } finally {
            sessionFactory.close();
        }


    }

}
