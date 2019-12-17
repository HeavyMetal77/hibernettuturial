package ua.tarastom.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {
        String db1 = "hb_student_tracker"; //схема БД + не забудь поменять название в hibernate.cfg.xml
        String jdbcUrl = "jdbc:mysql://localhost:3306/" + db1 + "?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";

        try {
            System.out.println("Connecting to database: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection successful!!!");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
