/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.auth.BaseRoleController;
import dal.AttendenceDBContext;
import dal.SessionDBContext;
import dal.SubjectDBContext;
import helper.DateTimeHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import model.Account;
import model.Attendence;
import model.Subject;

/**
 *
 * @author admin
 */
public class AttendController extends BaseRoleController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {

        String sessionid = req.getParameter("sessionid");        
        String gid = req.getParameter("gid");
        AttendenceDBContext attendDB = new AttendenceDBContext();
        SessionDBContext sessionDB = new SessionDBContext();
        int number = attendDB.getnum(sessionid);
        for (int i = 1; i <= number; i++) {
            Attendence a = new Attendence();
            a.setAttend(req.getParameter("attend" + i));
            boolean status = Boolean.parseBoolean(req.getParameter("status" + i));
            if (status != true) {
                status = false;
            }
            a.setStatus(status);

            attendDB.update(a);
        }
        sessionDB.updateSes(sessionid);
        ArrayList<Attendence> attendlist = attendDB.getlistStudent(sessionid);
        Date date = attendlist.get(1).getSession().getDate();

        boolean status = sessionDB.getStatus(sessionid);
         SubjectDBContext subjectDB = new SubjectDBContext();
        ArrayList<Subject> subjects = subjectDB.getListSubjectLec(account.getId());
        req.setAttribute("subjects", subjects);
        req.setAttribute("status", status);
        req.setAttribute("date", date);
        req.setAttribute("attend", attendlist);
        req.setAttribute("gid", gid);
        req.setAttribute("sessionid", sessionid);
        req.getRequestDispatcher("../view/lecturer/attend.jsp").forward(req, resp);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String sessionid = req.getParameter("sessionid");
        AttendenceDBContext attendDB = new AttendenceDBContext();
        ArrayList<Attendence> attendlist = attendDB.getlistStudent(sessionid);
        Date date = Date.valueOf(req.getParameter("date"));
        String lecturerid = account.getId();
        SessionDBContext sesDB = new SessionDBContext();
        String gid = sesDB.getGid(lecturerid);
        req.setAttribute("gid", gid);
        SessionDBContext sessionDB = new SessionDBContext();
        boolean status = sessionDB.getStatus(sessionid);
        SubjectDBContext subjectDB = new SubjectDBContext();
        ArrayList<Subject> subjects = subjectDB.getListSubjectLec(account.getId());
        req.setAttribute("subjects", subjects);
         req.setAttribute("date", date);
        req.setAttribute("status", status);
        req.setAttribute("attend", attendlist);
        req.setAttribute("sessionid", sessionid);
        req.getRequestDispatcher("../view/lecturer/attend.jsp").forward(req, resp);
    }

}
