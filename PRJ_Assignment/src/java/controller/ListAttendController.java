/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.auth.BaseRoleController;
import dal.AttendenceDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import model.Account;
import model.Attendence;
import model.Session;
import model.Student;

/**
 *
 * @author admin
 */
public class ListAttendController extends BaseRoleController {
   
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String gid = req.getParameter("gid");

        AttendenceDBContext attendDB = new AttendenceDBContext();
        SessionDBContext sessionDB = new SessionDBContext();
        StudentDBContext studentDB = new StudentDBContext();
        ArrayList<Attendence> attends = attendDB.getListAttend(gid);

        ArrayList<Session> sessions = sessionDB.ListSlot(gid);
        ArrayList<Student> students = studentDB.listStudent(gid);
        req.setAttribute("gid", gid);
        req.setAttribute("attends", attends);
        req.setAttribute("sessions", sessions);
        req.setAttribute("students", students);
        req.getRequestDispatcher("../view/lecturer/listattend.jsp").forward(req, resp);
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
