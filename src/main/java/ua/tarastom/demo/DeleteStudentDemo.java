package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Student;

public class DeleteStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();

        try{
            //способ 1
            currentSession.beginTransaction();
            int studentId = 1;
            Student student = currentSession.get(Student.class, studentId);
            currentSession.delete(student);
            currentSession.getTransaction().commit();

            //способ 2
            currentSession = sessionFactory.getCurrentSession();//после коммита обязательно получаем новую сессию
            currentSession.beginTransaction();
            currentSession.createQuery("delete from Student where id=4").executeUpdate();
            currentSession.getTransaction().commit();

        }finally {
            sessionFactory.close();
        }
    }
}
