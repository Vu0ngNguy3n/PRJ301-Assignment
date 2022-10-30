/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.auth.BaseRoleController;
import dal.AttendenceDBContext;
import dal.SubjectDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Account;
import model.Attendence;
import model.Subject;

/**
 *
 * @author admin
 */
public class ListAttendStudentController extends BaseRoleController {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String sid = account.getId();
        String gid = req.getParameter("gid");

        AttendenceDBContext attendDB = new AttendenceDBContext();
        ArrayList<Attendence> attends = attendDB.getListAttendStudent(sid, gid);

        SubjectDBContext subDB = new SubjectDBContext();
        ArrayList<Subject> subjects = subDB.list();
        
        req.setAttribute("attends", attends);
        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("../view/student/listattendstudent.jsp").forward(req, resp);
    
    }

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp, account);
    }

}
