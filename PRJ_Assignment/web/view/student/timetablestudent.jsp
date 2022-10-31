<%-- 
    Document   : timetablestudent
    Created on : Oct 29, 2022, 1:42:29 AM
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
        <title>TimeTable Student FAP</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
        <style>
            .content-table{

                width: 75%;
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
        <script >
            function slotStatus(status) {
                if(status == true){
                    alert('This slot had attended');
                }
                if(status == false){
                     alert('This slot had not attended');
                }
                 if(status == null){
                     alert('This slot will learn in future');
                }
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top" >
            <div class="container" >
                <a class="navbar-brand" href="${pageContext.request.contextPath}/student/timetable?lecturerid=${sessionScope.account.id}">
                    <img src="${pageContext.request.contextPath}/view/student/logo.jpg" alt="logo" height="36">
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
                        <li class="nav-item dropdown">
                            <a class="nav-link active" aria-current="page"  href="#" >List Attend <i class="fa-solid fa-caret-down"></i></a>
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
        <br/><div style="display: flex; justify-content: center;" ><h2>Activities for ${sessionScope.account.id} (${sessionScope.account.displayname})</h2></div><br/>
        <div style="display: flex; justify-content: center;"><table class="content-table">

                <thead >
                    <tr>
                        <th >
                            <form action="${pageContext.request.contextPath}/student/timetable?sid=${sessionScope.account.id}&from=${param.from}&to=${param.to}" method="GET">
                                Date From: <input type="date" name="from" value="${requestScope.from}"><br/>
                                Date To: <input type="date" name="to" value="${requestScope.to}"}>
                                <button type="submit" ><i class="fa-solid fa-eye"></i></button>

                            </form> 

                        </th>
                        <c:forEach items="${requestScope.datelist}" var="datelist">
                            <th>${datelist}<br/> ${helper.getDayNameofWeek(datelist)}</th> 
                            </c:forEach>

                    </tr>

                </thead>
                <tbody>
                    <c:forEach items="${requestScope.timeslots}" var="s">
                        <tr >
                            <td style="background-color: #A4C3A2;">Slot ${s.slot}<br/> ${s.time}</td>
                                <c:forEach items="${requestScope.datelist}" var="datelist">
                                <td align="center" >
                                    <c:set var="num" value="0">

                                    </c:set>
                                    <c:forEach items="${requestScope.sessions}" var="session">
                                        <c:if test="${helper.compare(datelist,session.date) eq 0 and (session.slot.slot eq s.slot)}">
                                            <c:set var="num" value="1"/>

                                            <a style='text-decoration: none '  href="#" onclick="slotStatus(${session.status})">${session.group.gid}</a><br/>

                                            at ${session.room.room} <br/>
                                            <c:if test="${session.status eq true  }">
                                                <i style="color: green   ">(Attend)</i>
                                            </c:if>
                                            <c:if test="${session.status eq false and (helper.compare(helper.dateToday(),datelist) == 1)}" >
                                                <i style="color: red   ">(Absent)</i>
                                            </c:if>
                                            <c:if test="${helper.compare(helper.dateToday(),datelist) == 0 and (session.status ne null)}">
                                                <i style="color: blue   ">(Happening)</i>
                                            </c:if>    
                                            <c:if test="${session.status ne null and (helper.compare(helper.dateToday(),datelist) < 0)}">
                                                <i style="color: yellow   ">(Not yet)</i>
                                            </c:if>

                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${num eq 0}">
                                        -
                                    </c:if>

                                </td>


                            </c:forEach>
                        </tr>
                    </c:forEach>



                </tbody>
            </table>
        </div>
        <div style="margin-left: 25%">
            <b>More note:</b><br/> 
            <ul>
                <li>(<font color="green">Attended</font>)<i> ${sessionScope.account.id} (${sessionScope.account.displayname}) had attended this slot</i></li>
                <li>(<font color="red">Absent</font>)<i> ${sessionScope.account.id} (${sessionScope.account.displayname}) had not attended this slot</li>
                <li>(<font color="yellow">Not yet</font>)<i> ${sessionScope.account.id} (${sessionScope.account.displayname}) will learn this slot in the future</li>
            </ul>

        </div>                    

    </body>
</html>
