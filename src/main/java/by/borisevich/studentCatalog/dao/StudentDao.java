package by.borisevich.studentCatalog.dao;

import by.borisevich.studentCatalog.model.Address;
import by.borisevich.studentCatalog.model.Student;
import org.apache.log4j.Logger;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private Logger log = Logger.getLogger(StudentDao.class);

    public StudentDao() {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/maksim/IdeaProjects/lab5/main.db", config.toProperties());

            Statement statement = con.createStatement();

            statement.execute("create table if not exists Student (" +
                    "id INTEGER primary key autoincrement," +
                    "surname varchar(255) not null," +
                    "name varchar(255) not null," +
                    "secondName varchar(255) not null," +
                    "groupNum integer not null," +
                    "city varchar(255) not null" +
                    ")");

            statement.execute("create table if not exists Address (" +
                    "id INTEGER primary key autoincrement," +
                    "street varchar(255) not null," +
                    "house integer not null," +
                    "flat integer not null," +
                    "student_id integer not null references Student" +
                    ")" );

            statement.execute("create unique index if not exists Address_id_uindex on Address (id)");
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addSomeStudents() {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/maksim/IdeaProjects/lab5/main.db", config.toProperties());

            Statement statement = con.createStatement();

            statement.execute("INSERT INTO Student (surname, name, secondName, groupNum, city) VALUES ('Borisevich', 'Maksim', 'Romanovich', 621702, 'Minsk'), ('Shokal', 'Irina', 'Dmitrievna', 621701, 'Minsk'), ('Gudilin', 'Andrei', 'Sergeevich', 621702, 'Minsk'), ('Pashkevich', 'Elena', 'Sergeevna', 621702, 'Minsk'), ('Anishcik', 'Andrei', 'Sergeevich', 621702, 'Minsk')");
            statement.execute("INSERT INTO Address (street, house, flat, student_id) VALUES ('Nezavisimost', 155, 105, 1), ('Nezavisimost', 155, 105, 2), ('Kalinouskava', 119, 25, 3), ('Kolasa', 28, 613, 4), ('Kolasa', 28, 603, 5)");

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/maksim/IdeaProjects/lab5/main.db", config.toProperties());
            if (student != null) {

                String insertStudentSQL = "INSERT INTO Student"
                        + "(surname, name, secondName, groupNum, city) VALUES"
                        + "(?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertStudentSQL);
                ps.setString(1, student.getSurname());
                ps.setString(2, student.getName());
                ps.setString(3, student.getSecondName());
                ps.setInt(4, student.getGroupNum());
                ps.setString(5, student.getCity());
                ps .executeUpdate();

                Statement statement = con.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT MAX(id) AS max_id FROM Student;");
                student.setId(resultSet.getInt(1));

                String insertAddressSQL = "INSERT INTO Address"
                        + "(street, house, flat, student_id) VALUES"
                        + "(?,?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(insertAddressSQL);
                preparedStatement.setString(1, student.getAddress().getStreet());
                preparedStatement.setInt(2, student.getAddress().getHouse());
                preparedStatement.setInt(3, student.getAddress().getFlat());
                preparedStatement.setInt(4, student.getId());
                preparedStatement .executeUpdate();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/maksim/IdeaProjects/lab5/main.db", config.toProperties());
            if (student != null) {
                String updateStudentSQL = "UPDATE Student SET surname=?, name=?, secondName=?, groupNum=?, city=? "
                        + "WHERE id=?";
                PreparedStatement ps = con.prepareStatement(updateStudentSQL);
                ps.setString(1, student.getSurname());
                ps.setString(2, student.getName());
                ps.setString(3, student.getSecondName());
                ps.setInt(4, student.getGroupNum());
                ps.setString(5, student.getCity());
                ps.setInt(6, student.getId());
                ps.executeUpdate();

                String updateAddressSQL = "UPDATE Address SET street=?, house=?, flat=?"
                        + "WHERE student_id=?";
                PreparedStatement preparedStatement = con.prepareStatement(updateAddressSQL);
                preparedStatement.setString(1, student.getAddress().getStreet());
                preparedStatement.setInt(2, student.getAddress().getHouse());
                preparedStatement.setInt(3, student.getAddress().getFlat());
                preparedStatement.setInt(4, student.getId());
                preparedStatement.executeUpdate();
                con.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> listStudents() {
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/maksim/IdeaProjects/lab5/main.db", config.toProperties());
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
            con.close();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getStudent(int id) {
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/maksim/IdeaProjects/lab5/main.db", config.toProperties());
            String getStudentQuery = "SELECT * FROM Student INNER JOIN Address ON Student.id = Address.student_id WHERE Student.id =?";
            PreparedStatement statement = con.prepareStatement(getStudentQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Student foundStudent = new Student();
            if (resultSet.next()) {
                foundStudent.setId(resultSet.getInt(1));
                foundStudent.setSurname(resultSet.getString(2));
                foundStudent.setName(resultSet.getString(3));
                foundStudent.setSecondName(resultSet.getString(4));
                foundStudent.setGroupNum(resultSet.getInt(5));
                foundStudent.setCity(resultSet.getString(6));
                foundStudent.setAddress(new Address(resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10)));
                con.close();
                return foundStudent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(int id) {
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/maksim/IdeaProjects/lab5/main.db", config.toProperties());
            String delStudentQuery = "DELETE FROM Student WHERE id=?";
            PreparedStatement statement = con.prepareStatement(delStudentQuery);
            statement.setInt(1, id);
            statement .executeUpdate();

            String delAddressQuery = "DELETE FROM Address WHERE student_id=?";
            PreparedStatement preparedStatement = con.prepareStatement(delAddressQuery);
            preparedStatement.setInt(1, id);
            preparedStatement .executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
