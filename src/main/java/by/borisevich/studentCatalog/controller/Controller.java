package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.StudentDao;
import by.borisevich.studentCatalog.model.Student;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Controller {

    private StudentDao studentDao;

    public Controller() {
        studentDao = new StudentDao();
    }

    public void showStudents(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"\n" +
                "integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"\n" +
                "        integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>");
        out.println("<link rel=\"stylesheet\" href=\"/lab5/css/style.css\" type=\"text/css\" >");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"clearfix\"></div>");
        out.println("<div class=\"container\">");
        out.println("<table class=\"main-table\">");
        out.println("<tr>");
        out.println("<td>№</td>");
        out.println("<td>ФИО</td>");
        out.println("<td>Группа</td>");
        out.println("<td>Город</td>");
        out.println("<td>Улица</td>");
        out.println("<td>Дом</td>");
        out.println("<td>Квартира</td>");
        out.println("</tr>");
        List<Student> students = studentDao.listStudents();
        if (students != null) {
            for (Student student : students) {
                out.println("<tr>");
                out.println("<td>" + student.getId() + "</td>");
                out.println("<td>" + student.getSurname() + " " + student.getName() + " " + student.getSecondName() + "</td>");
                out.println("<td>" + student.getGroupNum() + "</td>");
                out.println("<td>" + student.getCity() + "</td>");
                out.println("<td>" + student.getAddress().getStreet() + "</td>");
                out.println("<td>" + student.getAddress().getHouse() + "</td>");
                out.println("<td>" + student.getAddress().getFlat() + "</td>");
                out.println("</tr>");
            }
        }
        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
    }
}
