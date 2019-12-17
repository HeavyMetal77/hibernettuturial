package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Student;


public class UpdateStudentDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                                        .addAnnotatedClass(Student.class).buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();

        try{
            int studentId = 1;
            currentSession.beginTransaction();
            Student student = currentSession.get(Student.class, studentId);
            System.out.println(student);
            System.out.println("Updating student....");
            //имя обновляется только в коде
            student.setFirstName("Bob");

            //имя обновляется также и в базе после коммита
            currentSession.getTransaction().commit();

            // NEW CODE
            currentSession = sessionFactory.getCurrentSession(); //после коммита обязательно получаем новую сессию
            currentSession.beginTransaction();

            // update email for all students
            System.out.println("Update email for all students");
            currentSession.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

            // commit the transaction
            currentSession.getTransaction().commit();
            System.out.println("Done!");
        }finally {
            sessionFactory.close();
        }


    }
}
