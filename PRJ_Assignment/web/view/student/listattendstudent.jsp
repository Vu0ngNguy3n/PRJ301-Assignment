<%-- 
    Document   : listattendstudent
    Created on : Oct 30, 2022, 4:48:00 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="subject" class="dal.SubjectDBContext"/> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
        <title>View Attendence</title>
        <style>
            .content-table{
                width: 50%;
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
            body{
                background: #ededed;
            }
            .navbar-nav .nav-item{
                padding: 5px 5px;
            }
            .dropdown-menu{
                display: none;
                position: relative;
                background-color: #212529;
            }
            .dropdown-menu li a{
                top: 100%;
                line-height: 24px;
                padding: 0px 24px;
                color: #fff;
                text-decoration: none;
                text-transform: uppercase;
                display: inline-block;
            }
            .nav-item .nav-link{
                display: inline-block;
                position: relative;
            }
            .nav-item .dropdown-menu{
                position: absolute;
                background-color: #212529;
                display: block;
                display: none;
                list-style-type: none;
            }
            .navbar-nav >.nav-item:hover >a,
            .navbar-nav .dropdown-menu li:hover{
                background-color: #000;

            }
            .navbar-nav li:hover .dropdown-menu{
                display: block;
                padding: 2px 0px;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top" >
            <div class="container" >
                <a class="navbar-brand" href="${pageContext.request.contextPath}/student/timetable">
                    <img src="${pageContext.request.contextPath}/view/lecturer/logo.jpg" alt="logo" height="44">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                Hello ${sessionScope.account.displayname}
                                <i class="fa-solid fa-chalkboard-user"></i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/student/timetable">
                                TimeTable
                                <i class="fa-sharp fa-solid fa-table"></i>
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link active" aria-current="page"  href="#">List Attend <i class="fa-solid fa-caret-down"></i></a>
                            <ul class="dropdown-menu">
                                <c:forEach items="${requestScope.subjects}" var="s">
                                    <li><a href="${pageContext.request.contextPath}/student/listattend?gid=${subject.getgid(s.subjectid)}">${s.subjectid}</a></li>
                                    </c:forEach>
                            </ul>

                        </li>

                        <li class="nav-item ">
                            <a class="nav-link " href="${pageContext.request.contextPath}/logout" id="navbarDropdown" >
                                Logout
                                <i class="fa-solid fa-right-from-bracket"></i>
                            </a>
                            <
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div style="display: flex; justify-content: center">


            <table class="content-table">
                <tr style="background-color: #009879">
                    <th>NO</th>
                    <th>DATE</th>
                    <th>SLOT</th>
                    <th>ROOM</th>
                    <th>LECTURER</th>
                    <th>GROUP NAME</th>
                    <th>STATUS</th>
                </tr>

                <c:forEach items="${requestScope.attends}" var="s" varStatus="n">
                    <tr>
                        <td> ${n.count} </td>
                        <td >
                            <span style="background-color:#009879;display: inline;
                                  padding: 0.2em 0.6em 0.3em;
                                  font-size: 75%;
                                  font-weight: 700;
                                  line-height: 1;
                                  color: #fff;
                                  text-align: center;
                                  white-space: nowrap;
                                  vertical-align: baseline;
                                  border-radius: 0.25em; ">
                                ${s.session.date}
                            </span>
                        </td>
                        <td>
                            <span style="background-color:red;display: inline;
                                  padding: 0.2em 0.6em 0.3em;
                                  font-size: 75%;
                                  font-weight: 700;
                                  line-height: 1;
                                  color: #fff;
                                  text-align: center;
                                  white-space: nowrap;
                                  vertical-align: baseline;
                                  border-radius: 0.25em; ">
                                ${s.session.slot.slot}
                            </span>
                        </td>
                        <td>${s.session.room.room} <i class="fa-solid fa-building"></i></td>
                        <td style="text-transform: uppercase">${s.session.lecturer.lecturerid}</td>
                        <td>${s.session.group.gid}</td>
                        <td>
                            <c:if test="${s.status eq true }">
                                <font color='green'>
                                Present
                                </font>
                            </c:if>
                            <c:if test="${s.status eq false and (s.timerecord ne null) }">
                                <font color='red'>
                                Absent
                                </font>
                            </c:if>
                            <c:if test="${s.status eq false and (s.timerecord eq null) }">
                                <font color='black'>
                                Not yet
                                </font>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </body>
</html>
