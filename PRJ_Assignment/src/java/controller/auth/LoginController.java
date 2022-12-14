/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dal.AccountDBContext;
import dal.LecturerDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Account;
import model.Role;

/**
 *
 * @author admin
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int status = 0;
        if (request.getParameter("status") != null) {
            status = Integer.parseInt(request.getParameter("status"));
        }

        Cookie arr[] = request.getCookies();
        if (arr != null) {
            for (Cookie cookie : arr) {
                if (cookie.getName().equals("userC")) {
                    request.setAttribute("username", cookie.getValue());
                }
                if (cookie.getName().equals("passC")) {
                    request.setAttribute("password", cookie.getValue());
                }
            }
        }

        request.setAttribute("status", status);
        request.getRequestDispatcher("/view/login/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AccountDBContext accountDB = new AccountDBContext();
        Account account = accountDB.getAccount(username, password);
        if (account == null) {
            response.sendRedirect("login?status=2");
        } else {
            request.getSession().setAttribute("account", account);
            request.getSession().setMaxInactiveInterval(200);

            Cookie u = new Cookie("userC", username);
            Cookie p = new Cookie("passC", password);
            if (request.getParameter("remember") != null) {
                p.setMaxAge(200);
            } else {
                p.setMaxAge(0);
            }
            u.setMaxAge(200);

            response.addCookie(p);
            response.addCookie(u);

            for (Role role : account.getRoles()) {
                if (role.getRid() == 4) {
                    request.getRequestDispatcher("/student/timetable").forward(request, response);
                }
            }
            request.getRequestDispatcher("/lecturer/timetable").forward(request, response);
        }
    }

}
