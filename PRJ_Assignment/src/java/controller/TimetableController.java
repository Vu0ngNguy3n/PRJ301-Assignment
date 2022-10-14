/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.LecturerDBContext;
import dal.TimetableDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Lecturer;
import model.TimeTable;

/**
 *
 * @author admin
 */
public class TimetableController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lecturerid = req.getParameter("lecturerid");
         TimetableDBContext db = new TimetableDBContext();
         ArrayList<TimeTable> timetable = db.list(lecturerid);
         LecturerDBContext lecdb = new LecturerDBContext();
         ArrayList<Lecturer> lectlist  = lecdb.list(lecturerid);
         req.setAttribute("lecturer", lectlist);
         req.setAttribute("timetable", timetable);
         req.getRequestDispatcher("../view/timetable.jsp").forward(req, resp);
    }
    
    
    
}
