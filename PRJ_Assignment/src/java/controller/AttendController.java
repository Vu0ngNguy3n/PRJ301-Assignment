/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AttendenceDBContext;
import dal.SessionDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Account;
import model.Attendence;


/**
 *
 * @author admin
 */
public class AttendController extends HttpServlet {

    
   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sessionid = request.getParameter("sessionid");
        AttendenceDBContext attendDB = new AttendenceDBContext();
        ArrayList<Attendence> attendlist = attendDB.getlistStudent(sessionid);
        Account account = (Account)request.getSession().getAttribute("account");
        String lecturerid = account.getLecturerid();
        SessionDBContext sesDB = new SessionDBContext();
        String gid = sesDB.getGid(lecturerid);
        request.setAttribute("gid", gid);

        request.setAttribute("attend", attendlist);
        request.setAttribute("sessionid", sessionid);
        request.getRequestDispatcher("../view/lecturer/attend.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account)request.getSession().getAttribute("account");
        String sessionid = request.getParameter("sessionid");
        
        String gid = request.getParameter("gid");
        AttendenceDBContext attendDB = new AttendenceDBContext();
        SessionDBContext sessionDB = new SessionDBContext();
        int number = attendDB.getnum(sessionid);
        for(int i=1; i<= number; i++){
            Attendence a = new Attendence();
            a.setAttend(request.getParameter("attend"+i));
            boolean status = Boolean.parseBoolean(request.getParameter("status"+i));
            if(status != true){
                status= false;
            }
            a.setStatus(status);
            
            attendDB.update(a);
        }
        sessionDB.updateSes(sessionid);
        ArrayList<Attendence> attendlist = attendDB.getlistStudent(sessionid);
        
        request.setAttribute("attend", attendlist);
        request.setAttribute("gid", gid);
        request.setAttribute("sessionid", sessionid);
        request.getRequestDispatcher("../view/lecturer/attend.jsp").forward(request, response);
    }
   
}
