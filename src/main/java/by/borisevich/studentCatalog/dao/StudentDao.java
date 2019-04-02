package by.borisevich.studentCatalog.dao;

import by.borisevich.studentCatalog.model.Address;
import by.borisevich.studentCatalog.model.Student;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private Logger log = Logger.getLogger(StudentDao.class);
    private static final String url = "jdbc:mysql://localhost:3306/studentList?useUnicode=true&characterEncoding=utf8";
    private static final String user = "root";
    private static final String password = "root";

    public StudentDao() {
        try {
            log.info("creating datatables");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            Statement statement = con.createStatement();

            statement.execute("create table if not exists Student (" +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "surname varchar(255) not null," +
                    "name varchar(255) not null," +
                    "secondName varchar(255) not null," +
                    "groupNum int not null," +
                    "city varchar(255) not null," +
                    "PRIMARY KEY (id))");

            statement.execute("create table if not exists Address (" +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "street varchar(255) not null," +
                    "house int not null," +
                    "flat int not null," +
                    "PRIMARY KEY (id)," +
                    "FOREIGN KEY (id) REFERENCES Student(id)" +
                    ")" );

//            statement.execute("create unique index if not exists Address_id_uindex on Address (id)");
            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addSomeStudents() {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement statement = con.createStatement();
            statement.execute("INSERT INTO Student (surname, name, secondName, groupNum, city) VALUES ('Borisevich', 'Maksim', 'Romanovich', 621702, 'Minsk'), ('Shokal', 'Irina', 'Dmitrievna', 621701, 'Minsk'), ('Gudilin', 'Andrei', 'Sergeevich', 621702, 'Minsk'), ('Pashkevich', 'Elena', 'Sergeevna', 621702, 'Minsk'), ('Anishcik', 'Andrei', 'Sergeevich', 621702, 'Minsk')");
            statement.execute("INSERT INTO Address (street, house, flat) VALUES ('Nezavisimost', 155, 105), ('Nezavisimost', 155, 105), ('Kalinouskava', 119, 25), ('Kolasa', 28, 613), ('Kolasa', 28, 603)");

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
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

                String insertAddressSQL = "INSERT INTO Address"
                        + "(street, house, flat) VALUES"
                        + "(?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(insertAddressSQL);
                preparedStatement.setString(1, student.getAddress().getStreet());
                preparedStatement.setInt(2, student.getAddress().getHouse());
                preparedStatement.setInt(3, student.getAddress().getFlat());
                preparedStatement .executeUpdate();
                con.close();
                log.info("student " + student.getSurname() + " added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            if (student != null) {
                String updateAddressSQL = "UPDATE Address SET street=?, house=?, flat=?"
                        + " WHERE Address.id=?;";
                PreparedStatement preparedStatement = con.prepareStatement(updateAddressSQL);
                preparedStatement.setString(1, student.getAddress().getStreet());
                preparedStatement.setInt(2, student.getAddress().getHouse());
                preparedStatement.setInt(3, student.getAddress().getFlat());
                preparedStatement.setInt(4, student.getId());
                preparedStatement.executeUpdate();

                String updateStudentSQL = "UPDATE Student SET surname=?, name=?, secondName=?, groupNum=?, city=? "
                        + " WHERE id=?";
                PreparedStatement ps = con.prepareStatement(updateStudentSQL);
                ps.setString(1, student.getSurname());
                ps.setString(2, student.getName());
                ps.setString(3, student.getSecondName());
                ps.setInt(4, student.getGroupNum());
                ps.setString(5, student.getCity());
                ps.setInt(6, student.getId());
                ps.executeUpdate();

                con.close();
                log.info("student " + student.getSurname() + " updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> listStudents() {
        List<Student> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement statement = con.createStatement();
            String userQuery = "SELECT * FROM Student INNER JOIN Address ON Student.id = Address.id";
            ResultSet resultSet = statement.executeQuery(userQuery);
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Student> getStudentsList(int start,int total) {
        List<Student> list=new ArrayList<>();
        try{
            Connection con =  DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement("SELECT * from Student INNER JOIN Address ON Student.id = Address.id limit "+(start-1)+","+total);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
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
        }catch(SQLException e){
            e.printStackTrace();;
        }
        return list;
    }

    public Student getStudent(int id) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String getStudentQuery = "SELECT * FROM Student INNER JOIN Address ON Student.id = Address.id WHERE Student.id =?";
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

                log.info("found student " + foundStudent.getSurname());
                return foundStudent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(int id) {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String delAddressQuery = "DELETE FROM Address WHERE id=?";
            PreparedStatement preparedStatement = con.prepareStatement(delAddressQuery);
            preparedStatement.setInt(1, id);
            preparedStatement .executeUpdate();

            String delStudentQuery = "DELETE FROM Student WHERE id=?";
            PreparedStatement statement = con.prepareStatement(delStudentQuery);
            statement.setInt(1, id);
            statement .executeUpdate();

            con.close();
            log.info("student №" + id + " updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Student getLastStudent() {
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String getStudentQuery = "SELECT * FROM Student INNER JOIN Address ON Student.id = Address.id WHERE Student.id = (SELECT max(id) FROM Student)";
            PreparedStatement statement = con.prepareStatement(getStudentQuery);
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

                log.info("found student " + foundStudent.getSurname());
                return foundStudent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getColOfRecords() {
        int colOfRecords = 0;
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            String getColOfRecordsQuery = "SELECT COUNT(id) FROM Student";
            PreparedStatement statement = con.prepareStatement(getColOfRecordsQuery);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                colOfRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colOfRecords;
    }
}
