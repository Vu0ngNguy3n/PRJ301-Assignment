/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AttendenceDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Account;
import model.Attendence;
import model.Session;
import model.Student;

/**
 *
 * @author admin
 */
public class ListAttendController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        Account account = (Account)request.getSession().getAttribute("account");
        String gid = request.getParameter("gid");
        
        
        AttendenceDBContext attendDB = new AttendenceDBContext();
        SessionDBContext sessionDB = new SessionDBContext();
        StudentDBContext studentDB = new StudentDBContext();
        ArrayList<Attendence> attends = attendDB.getListAttend(gid);
        ArrayList<Session> sessions = sessionDB.ListSlot(gid);
        ArrayList<Student> students = studentDB.listStudent(gid);
       
        request.setAttribute("gid", gid);
        request.setAttribute("attends", attends);
        request.setAttribute("sessions", sessions);
        request.setAttribute("students", students);
        request.getRequestDispatcher("../view/lecturer/listattend.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
