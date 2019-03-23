package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.StudentDao;
import by.borisevich.studentCatalog.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {

    private StudentDao dao;

    public StudentServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dao = new StudentDao();
        List<Student> students = dao.listStudents();
        if (students != null) {
            req.setAttribute("students", students);
        }

        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buttonValue = req.getParameter("studentButton");
        String studentId = buttonValue.substring(buttonValue.length()-1);
        if (buttonValue.contains("delStudent")) {
            dao.deleteStudent(Integer.parseInt(studentId));
            doGet(req,resp);
        } else if (buttonValue.contains("editStudent")) {
            Student student = dao.getStudent(Integer.parseInt(studentId));
            req = setReqParams(req, student);
            req.getRequestDispatcher("view/addStudent.jsp").forward(req, resp);
        }
    }

    private HttpServletRequest setReqParams(HttpServletRequest req, Student student) {
        req.setAttribute("studentID", student.getId());
        req.setAttribute("studentFIO", student.getSurname() + " " + student.getName() + " " + student.getSecondName());
        req.setAttribute("studentGroup", student.getGroupNum());
        req.setAttribute("studentCity", student.getCity());
        req.setAttribute("studentStreet", student.getAddress().getStreet());
        req.setAttribute("studentHouse", student.getAddress().getHouse());
        req.setAttribute("studentFlat", student.getAddress().getFlat());
        return req;
    }
}
