package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.StudentDao;
import by.borisevich.studentCatalog.model.Address;
import by.borisevich.studentCatalog.model.Student;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStudentServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(AddStudentServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("addStudent get request");
        req.getRequestDispatcher("view/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("addStudent post request");
        StudentDao dao = new StudentDao();
        Student student = createStudent(req);
        if (!req.getParameter("studentID").equals("")) {
            student.setId(Integer.parseInt(req.getParameter("studentID")));
            dao.updateStudent(student);
        } else {
            dao.addStudent(student);
        }
        req.getRequestDispatcher("view/addStudent.jsp").forward(req, resp);
    }

    private Student splitFIO(HttpServletRequest req) {
        logger.info("going to split full name");
        Student student = new Student();
        String fullName = req.getParameter("studentFullName");
        String[] fullNameSplit = fullName.split(" ");

        if (fullNameSplit.length != 3) {
            System.out.println("Error!");
        } else {
            student.setSurname(fullNameSplit[0]);
            student.setName(fullNameSplit[1]);
            student.setSecondName(fullNameSplit[2]);
        }
        logger.info("full name splitted");
        return student;
    }

    private Student createStudent(HttpServletRequest req) {
        Student student = splitFIO(req);
        student.setGroupNum(Integer.parseInt(req.getParameter("studentGroup")));
        student.setCity(req.getParameter("studentCity"));
        Address address = new Address();
        address.setStreet(req.getParameter("studentStreet"));
        address.setHouse(Integer.parseInt(req.getParameter("studentHouse")));
        address.setFlat(Integer.parseInt(req.getParameter("studentFlat")));
        student.setAddress(address);

        return student;
    }
}
