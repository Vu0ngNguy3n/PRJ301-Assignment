/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;

/**
 *
 * @author admin
 */
public abstract class BaseAuthenticationController extends HttpServlet{
    
    private boolean isAuthentcated(HttpServletRequest request){
        Account account  = (Account)request.getSession().getAttribute("account");
        return account!=null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isAuthentcated(req)){
            processPost(req, resp);
        }else{
           resp.sendRedirect("/PRJ_Assignment/login");
        }
    }

    protected abstract void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
     protected abstract void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isAuthentcated(req)){
            processGet(req, resp);
        }else{
            resp.sendRedirect("/PRJ_Assignment/login");
        }
    }
    
}
