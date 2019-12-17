package ua.tarastom.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.tarastom.entity.Student;

import java.util.List;

/*
если IDEA подчеркивает красным аннотированный класс (from Student) ("Cannot resolve simbol")
- https://ru.stackoverflow.com/questions/763151/%D0%A0%D0%B0%D0%B1%D0%BE%D1%82%D0%B0-%D1%81-hql-hibernate

Скорее всего у вас не включена интеграции идеи и БД. Идея подчеркивает потому,
что ничего не знает про схему вашей базы.
В левом столбце снизу (левее дерева файлов) у вас должны быть вкладка Persistence.
Откройте её, там должно быть имя ваше БД. ПКМ на ней и "Assign Data Sources..."
В открывшемся окне слева надо указать коннект к БД и сохранить.
Если вкладки Persistence нет, тогда :
Ctrl + Shift + Alt + S
Facets
добавить jpa и внизу выбрать Default JPA provider - Hibernate
По факту даже не делая этого у вас все должно работать, если все сделали правильно.
Т.к. это ошибка интеграции Intellij IDEA а не проекта
 */

public class QueryStudentDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Student.class).buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        try {
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // display the students
            displayStudents(theStudents);

            // query students: lastName='Doe'
            theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            // display the students
            System.out.println("\n\nStudents who have last name of Doe");
            displayStudents(theStudents);

            // query students: lastName='Doe' OR firstName='Daffy'
            theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList();

            System.out.println("\n\nStudents who have last name of Doe OR first name Daffy");
            displayStudents(theStudents);

            // query students where email LIKE '%gmail.com'
            theStudents = session.createQuery("from Student s where s.email LIKE '%ukr.net'").getResultList();

            System.out.println("\n\nStudents whose email ends with ukr.net");
            displayStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
