<%-- 
    Document   : attend
    Created on : Oct 17, 2022, 8:29:38 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="helper" class="helper.DateTimeHelper"/>
<jsp:useBean id="subject" class="dal.SubjectDBContext"/> 

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
            .navbar-nav .nav-item{
                padding: 5px 5px;
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
                <a class="navbar-brand" href="${pageContext.request.contextPath}/lecturer/timetable">
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
                            <a class="nav-link " aria-current="page" href="timetable?lecturerid=${sessionScope.account.id}">
                                TimeTable
                                <i class="fa-sharp fa-solid fa-table"></i>
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link active" aria-current="page"  href="#">List Attend <i class="fa-solid fa-caret-down"></i></a>
                            <ul class="dropdown-menu">
                                <c:forEach items="${requestScope.subjects}" var="s">
                                    <li><a href="${pageContext.request.contextPath}/lecturer/listattend?gid=${subject.getgid(s.subjectid)}">${s.subjectid}</a></li>
                                    </c:forEach>
                            </ul>

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
                        
                        <c:if test="${helper.compare(requestScope.date,helper.dateToday()) == 0}">
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
                        </c:if>
                        <c:if test="${helper.compare(requestScope.date,helper.dateToday()) < 0}">
                            <c:if test="${attend.status eq true}">
                                <i style="color: green">Attended</i>
                            </c:if>
                            <c:if test="${attend.status eq false}">
                                <i style="color: red">Absent</i>
                            </c:if>
                        </c:if>    
                        <c:if test="${helper.compare(requestScope.date,helper.dateToday()) > 0}">
                            <i style="color: black">Not Yet</i>
                        </c:if>        

                    </td>
                    <td>${attend.timerecord}</td>
                    </tr>
                </c:forEach>


            </table>

            
                <input 
                    <c:if test="${helper.compare(requestScope.date,helper.dateToday()) != 0}">
                        type="hidden"
                   </c:if>
                    type="submit" 
                    value="Submit">
          
        </form>
    </body>
</html>
