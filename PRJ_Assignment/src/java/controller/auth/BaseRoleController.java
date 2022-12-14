/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;
import model.Feature;
import model.Role;

/**
 *
 * @author admin
 */
public abstract class BaseRoleController extends BaseAuthenticationController{

    private boolean isAuthorized(HttpServletRequest req){
        Account account = (Account)req.getSession().getAttribute("account");
        String currentURL = req.getServletPath();
        for (Role role : account.getRoles()) {
            for (Feature feature : role.getFeatures()) {
                if(feature.getUrl().equals(currentURL)){
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isAuthorized(req)){
            processPost(req, resp, (Account)req.getSession().getAttribute("account"));
        }else{
            Account account = (Account)req.getSession().getAttribute("account");
            if(account.getId().startsWith("HE")){
                req.getRequestDispatcher("/student/timetable").forward(req, resp);
            }else{
                req.getRequestDispatcher("/lecturer/timetable").forward(req, resp);
            }
            
        }
    }
    
    protected abstract void processPost(HttpServletRequest req, HttpServletResponse resp,Account account) throws ServletException, IOException;
    protected abstract void processGet(HttpServletRequest req, HttpServletResponse resp,Account account) throws ServletException, IOException;
    
    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isAuthorized(req)){
            processGet(req, resp, (Account)req.getSession().getAttribute("account"));
        }else{
              Account account = (Account)req.getSession().getAttribute("account");
            if(account.getId().startsWith("HE")){
                req.getRequestDispatcher("/student/timetable").forward(req, resp);
            }else{
                req.getRequestDispatcher("/lecturer/timetable").forward(req, resp);
            }
        }
    }
    
}
