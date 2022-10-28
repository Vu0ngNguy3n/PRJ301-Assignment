/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.auth.BaseRoleController;
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
public class AttendController extends BaseRoleController {

    
   

   
    

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
         
        String sessionid = req.getParameter("sessionid");
        
        String gid = req.getParameter("gid");
        AttendenceDBContext attendDB = new AttendenceDBContext();
        SessionDBContext sessionDB = new SessionDBContext();
        int number = attendDB.getnum(sessionid);
        for(int i=1; i<= number; i++){
            Attendence a = new Attendence();
            a.setAttend(req.getParameter("attend"+i));
            boolean status = Boolean.parseBoolean(req.getParameter("status"+i));
            if(status != true){
                status= false;
            }
            a.setStatus(status);
            
            attendDB.update(a);
        }
        sessionDB.updateSes(sessionid);
        ArrayList<Attendence> attendlist = attendDB.getlistStudent(sessionid);
        
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
        
        String lecturerid = account.getId();
        SessionDBContext sesDB = new SessionDBContext();
        String gid = sesDB.getGid(lecturerid);
        req.setAttribute("gid", gid);

        req.setAttribute("attend", attendlist);
        req.setAttribute("sessionid", sessionid);
        req.getRequestDispatcher("../view/lecturer/attend.jsp").forward(req, resp);
    }
   
}
