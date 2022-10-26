<%-- 
    Document   : attend
    Created on : Oct 17, 2022, 8:29:38 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FAP Attend</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
        <style>


            .content-table{
                width: 100%;
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
                    <img src="view/lecturer/logo.jpg" alt="logo" height="44">
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

        <br/><h2>${requestScope.sessionid} </h2><br/>
        <form action="attend" method="POST">
            <input type="hidden" name="lecturerid" value="${requestScope.lecturerid}">
            <input type="hidden" name="sessionid" value="${requestScope.sessionid}">
            <input type="hidden" name="gid" value="${requestScope.gid}">

            <table class="content-table" >
                <tr style="background-color: #009879">
                    <th>StudentID</th>
                    <th>Name</th>
                    <th>Group</th>
                    <th>Status</th>
                    <th>RecordTime</th>

                </tr>

                <c:forEach items="${requestScope.attend}" var="attend" varStatus="a">
                    <tr>
                    <input type="hidden" name="attend${a.count}" value="${attend.attend}" >
                    <td >${attend.student.sid}</td>
                    <td>${attend.student.sname}</td>
                    <td>${attend.session.group.gid}</td>
                    <td>
                        <input
                            <c:if test="${attend.status}">
                                checked="checked"
                            </c:if>
                            type="radio" name="status${a.count}" value="true" > Attend
                        <input
                            <c:if test="${!attend.status or(attend.status eq null)}">
                                checked="checked"
                            </c:if>
                            type="radio" name="status${a.count}" value="false" > Absent
                    </td>
                    <td>${attend.timerecord}</td>
                    </tr>
                </c:forEach>


            </table>

            <input type="submit" value="Submit">
        </form>
    </body>
</html>
