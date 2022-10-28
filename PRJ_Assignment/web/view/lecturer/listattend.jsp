<%-- 
    Document   : listattend
    Created on : Oct 21, 2022, 8:00:09 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
        <title>JSP Page</title>
        <style>
            .content-table{
               
                margin-left: auto;
                margin-right: auto;
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 0.9em;
                min-width: 400px;
                border-radius: 5px 5px 0 0;
                overflow: hidden;
                box-shadow: 0 0 20px rgba(0,0,0,.15);
            }
            .content-table thead tr{
                background-color: #009879;
                color: #ffffff;
                text-align: left;
                font-weight: bold;
            }
            th, td {
                border: 1px solid #009879;
            }
            .content-table th,
            .content-table td{
                padding: 12px 15px;
            }
            .content-table tbody tr{
                border-bottom: 1px solid #dddddd;
            }
            .content-table tbody tr:nth-of-type(even){
                background-color: #f3f3f3;
            }
            .content-table tbody tr:last-of-type{
                border-bottom: 2px solid #009879;
            }
        </style>
    </head>
    <body>
        
         <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top" >
            <div class="container" >
                <a class="navbar-brand" href="#">
                    <img src="${pageContext.request.contextPath}/view/lecturer/logo.jpg" alt="logo" height="44">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Hello Mr.${sessionScope.account.displayname}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="timetable?lecturerid=${sessionScope.account.lecturerid}">
                                TimeTable
                               <i class="fa-sharp fa-solid fa-table"></i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="/PRJ_Assignment/lecturer/listattend?gid=${requestScope.gid}&lecturerid=${sessionScope.account.lecturerid}">
                                Attend Of This Class
                                <i class="fa-solid fa-list-check"></i>
                            </a>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link " href="/PRJ_Assignment/logout" id="navbarDropdown" >
                                Logout
                                <i class="fa-solid fa-right-from-bracket"></i>
                            </a>
                            <
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <a href="timetable?lecturerid=${sessionScope.account.lecturerid}">Home</a>
        <input style="display: block" type="text" name="gid" value="${requestScope.gid}">
        <table class="content-table">
            <tr>
                <th></th>
                    <c:forEach items="${requestScope.sessions}" var="ses" varStatus="s">
                    <th style="background-color: #009879" >${ses.sessionid}</th>
                    </c:forEach>
                <th style="background-color: #009879">Attendence Report</th>
            </tr>

            <c:forEach items="${requestScope.students}" var="students">
                <tr>
                    <c:set var="attended" value="0"/>
                    <c:set var="total" value="0"/>
                    <td style="background-color: #A4C3A2;">${students.sid} - ${students.sname}</td>
                    <c:forEach items="${requestScope.sessions}" var="ses" >

                        <c:forEach items="${requestScope.attends}" var="att">
                            <c:if test="${att.student.sid eq students.sid and (att.session.sessionid eq ses.sessionid)}">
                                <td>
                                    <c:if test="${att.timerecord ne null}">
                                        <c:set var="total" value="${total+1}"/>

                                        <c:if test="${att.status eq true}">
                                            <i style="color: green" class="fa-sharp fa-solid fa-check"></i>
                                            <c:set var="attended" value="${attended+1}"/>
                                        </c:if>
                                        <c:if test="${att.status ne true}">
                                            <i style="color: red" class="fa-solid fa-x"></i>
                                        </c:if> 
                                    </c:if>

                                    <c:if test="${att.timerecord eq null}">
                                        <i style="color: red" >Not yet</i>
                                    </c:if>

                                </td>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    <td>
                        ${attended}/${total} <br/>
                        <c:if test="${attended/total <= 0.8}">
                           <i style="color: red" class="fa-solid fa-face-tired"></i>
                        </c:if>
                        <c:if test="${attended/total >= 0.8}">
                             <i style="color: green" class="fa-sharp fa-solid fa-face-smile"></i>
                        </c:if>
                    </td>       

                </tr>
            </c:forEach>
        </table>
    </body>
</html>
