package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Student;


public class PrimaryKeyDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try{
            //ALTER TABLE hb_student_tracker.student AUTO_INCREMENT=3000   - установка автоинкремента с 3000
            //truncate hb_student_tracker.student   - очистка базы
            Student student1 = new Student("Taras", "Tom", "example2@ukr.net");
            Student student2 = new Student("Maxim", "Doe", "example3@ukr.net");
            Student student3 = new Student("Ura", "Boe", "example4@ukr.net");
            session.beginTransaction();
            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.getTransaction().commit();

        }finally {
            sessionFactory.close();
        }
    }
}
