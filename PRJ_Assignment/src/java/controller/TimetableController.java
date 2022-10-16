/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LecturerDBContext;
import dal.TimeslotDBContext;
import helper.DateTimeHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import model.Lecturer;
import model.TimeSlot;


/**
 *
 * @author admin
 */
public class TimetableController extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lecturerid = request.getParameter("lecturerid");
        String from_e = request.getParameter("from");
        String to_e = request.getParameter("to");
        java.sql.Date from =null;
        java.sql.Date to = null;
        if(from_e == null || from_e.length()== 0 || to_e == null || to_e.length()==0){
            Date today = new Date();
            int todayOfWeek = DateTimeHelper.getDayofWeek(today);
            if(todayOfWeek == 1){
                todayOfWeek = 8;
            }
            Date e_from = DateTimeHelper.addDays(today, 2-todayOfWeek);
            Date e_to = DateTimeHelper.addDays(today, 8-todayOfWeek);
            from = DateTimeHelper.toDateSql(e_from);
            to = DateTimeHelper.toDateSql(e_to);
        }else{
            from = java.sql.Date.valueOf(from_e);
            to = java.sql.Date.valueOf(to_e);
        }
        
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("datelist", DateTimeHelper.getDateList(from, to));
        
        LecturerDBContext lecDB = new LecturerDBContext();
        ArrayList<Lecturer> lecturer = lecDB.list();
        request.setAttribute("lecturer", lecturer);
        
        TimeslotDBContext timeDB = new TimeslotDBContext();
        ArrayList<TimeSlot> timeslot = timeDB.list();
        request.setAttribute("slots", timeslot);
        
        
        request.getRequestDispatcher("../view/lecturer/timetable.jsp").forward(request, response);
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}