/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.auth.BaseRoleController;
import dal.AttendenceDBContext;
import dal.SessionDBContext;
import dal.SubjectDBContext;
import dal.TimeslotDBContext;
import helper.DateTimeHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Attendence;
import model.Session;
import model.Subject;
import model.TimeSlot;

/**
 *
 * @author admin
 */
public class TimetableStudentController extends BaseRoleController {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String sid = account.getId();
        String from_e = req.getParameter("from");
        String to_e = req.getParameter("to");
        java.sql.Date from = null;
        java.sql.Date to = null;

        SessionDBContext sessionDB = new SessionDBContext();
        if (from_e == null || from_e.length() == 0 || to_e == null || to_e.length() == 0 || DateTimeHelper.compare(java.sql.Date.valueOf(from_e), java.sql.Date.valueOf(to_e)) == 1) {
            Date today = new Date();
            int todayOfWeek = DateTimeHelper.getDayofWeek(today);
            if (todayOfWeek == 1) {
                todayOfWeek = 8;
            }
            Date e_from = DateTimeHelper.addDays(today, 2 - todayOfWeek);
            Date e_to = DateTimeHelper.addDays(today, 8 - todayOfWeek);
            from = DateTimeHelper.toDateSql(e_from);
            to = DateTimeHelper.toDateSql(e_to);
        } else {
            from = java.sql.Date.valueOf(from_e);
            to = java.sql.Date.valueOf(to_e);
        }

        TimeslotDBContext timeslotdb = new TimeslotDBContext();
        ArrayList<TimeSlot> timeslots = timeslotdb.list();
        ArrayList<Session> sessions = sessionDB.getListSessionStudent(sid, from, to);
        SubjectDBContext subDB = new SubjectDBContext();
        ArrayList<Subject> subjects = subDB.list();
        req.setAttribute("subjects", subjects);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("datelist", DateTimeHelper.getDateList(from, to));
        req.setAttribute("timeslots", timeslots);
        req.setAttribute("sessions", sessions);
        req.getRequestDispatcher("../view/student/timetablestudent.jsp").forward(req, resp);
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
