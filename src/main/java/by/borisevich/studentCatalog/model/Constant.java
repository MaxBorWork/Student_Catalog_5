package by.borisevich.studentCatalog.model;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;

public class Constant {

    public static final String dbUrl = "jdbc:mysql://localhost:3306/studentList?useUnicode=true&characterEncoding=utf8";
    public static final String dbUser = "root";
    public static final String dbPassword = "root";


    public static final String SQL_CREATE_STUDENT_TABLE = "create table if not exists Student (" +
                                                                "id int NOT NULL AUTO_INCREMENT," +
                                                                "surname varchar(255) not null," +
                                                                "name varchar(255) not null," +
                                                                "secondName varchar(255) not null," +
                                                                "groupNum int not null," +
                                                                "city varchar(255) not null," +
                                                                "PRIMARY KEY (id))";

    public static final String SQL_CREATE_ADDRESS_TABLE = "create table if not exists Address (" +
                                                                "id int NOT NULL AUTO_INCREMENT," +
                                                                "street varchar(255) not null," +
                                                                "house varchar(255) not null," +
                                                                "flat varchar(255) not null," +
                                                                "PRIMARY KEY (id)," +
                                                                "FOREIGN KEY (id) REFERENCES Student(id)" +
                                                                ")";

//    public static final String SQL_CREATE_GROUP_TABLE = "create table if not exists Group (" +
//                                                                "id int NOT NULL AUTO_INCREMENT," +
//                                                                "groupNum varchar(255) not null," +
//                                                                "house varchar(255) not null," +
//                                                                "PRIMARY KEY (id)" +
//                                                                ")";



    public static final String SQL_SELECT_LAST_RECORD = "SELECT * FROM Student INNER JOIN " +
                                                            "Address ON Student.id = Address.id " +
                                                            "WHERE Student.id = (SELECT max(id) FROM Student)";

    public static final String SQL_GET_COL_OF_RECORDS = "SELECT COUNT(id) FROM Student";

    public static final String SQL_SELECT_ALL_RECORDS = "SELECT * FROM Student INNER JOIN Address ON Student.id = Address.id";

    public static final String SQL_UPDATE_STUDENT_QUERY = "UPDATE Student " +
                                                                "SET surname=?, name=?, secondName=?, groupNum=?, city=? " +
                                                                " WHERE id=?";

    public static final String SQL_UPDATE_ADDRESS_QUERY = "UPDATE Address SET street=?, house=?, flat=?" +
                                                                " WHERE Address.id=?;";

    public static final String SQL_DELETE_STUDENT_QUERY = "DELETE FROM Student WHERE id=?";

    public static final String SQL_DELETE_ADDRESS_QUERY = "DELETE FROM Address WHERE id=?";

    public static final String SQL_SELECT_STUDENT_BY_ID = "SELECT * FROM Student INNER JOIN " +
                                                                "Address ON Student.id = Address.id " +
                                                                "WHERE Student.id =?";

    public static final String SQL_INSERT_STUDENT_QUERY = "INSERT INTO Student" +
                                                                "(surname, name, secondName, groupNum, city)" +
                                                                "VALUES (?,?,?,?,?)";

    public static final String SQL_INSERT_ADDRESS_QUERY = "INSERT INTO Address" +
                                                                "(street, house, flat) " +
                                                                "VALUES (?,?,?)";

    public static void loggerConfig(Logger logger) {
        logger.setLevel(Level.ALL);
        PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
        try {
            FileAppender fileAppender = new FileAppender(layout, "log_info.log");
            fileAppender.setAppend(false);
            fileAppender.setImmediateFlush(true);
            logger.addAppender(fileAppender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
