package by.borisevich.studentCatalog.dao;

import by.borisevich.studentCatalog.model.Address;
import by.borisevich.studentCatalog.model.Student;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private Logger log = Logger.getLogger(StudentDao.class);

    public StudentDao() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/maksim/IdeaProjects/lab5/main.db");

            Statement statement = con.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS Student (" +
                    "id integer PRIMARY KEY," +
                    "surname varchar(255) NOT NULL," +
                    "name varchar(255) NOT NULL," +
                    "secondName varchar(255) NOT NULL," +
                    "groupNum varchar(255) NOT NULL," +
                    "city varchar(255) NOT NULL)");

            statement.execute("CREATE TABLE IF NOT EXISTS Address (" +
                    "id integer PRIMARY KEY," +
                    "street varchar(255) NOT NULL," +
                    "house varchar(255) NOT NULL," +
                    "flat varchar(255) NOT NULL," +
                    "student_id varchar(255) NOT NULL)");

//            statement.execute("INSERT INTO Address VALUES (1, 'Nezavisimost', 155, 105, 1), (2, 'Kolasa', 28, 613, 2), (3, 'Kolasa', 28, 603, 3), (4, 'Kalinouskava', 119, 25, 4), (5, 'Nezavisimost', 44, 15, 5)");
//
//            statement.execute("INSERT INTO Student VALUES (1, 'Borisevich', 'Maksim', 'Romanovich', 621702, 'Minsk'), (2, 'Shokal', 'Irina', 'Dmitrievna', 621701, 'Minsk'), (3, 'Gudilin', 'Andrei', 'Sergeevich', 621702, 'Minsk'), (4, 'Pashkevich', 'Elena', 'Sergeevna', 621702, 'Minsk'), (5, 'Anishcik', 'Andrei', 'Sergeevich', 621702, 'Minsk')");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Student> listStudents() {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/maksim/IdeaProjects/lab5/main.db");

            Statement statement = con.createStatement();

            String userQuery = "SELECT * FROM Student INNER JOIN Address ON Student.id = Address.student_id";
            ResultSet resultSet = statement.executeQuery(userQuery);
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Student(resultSet.getInt(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4),
                                        resultSet.getInt(5),
                                        resultSet.getString(6),
                                        new Address(resultSet.getInt(7),
                                                    resultSet.getString(8),
                                                    resultSet.getInt(9),
                                                    resultSet.getInt(10))));
            }

            return list;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
