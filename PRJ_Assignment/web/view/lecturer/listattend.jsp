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
        <title>JSP Page</title>
        <style>
            *{
                margin: 0;
                padding: 0;
                font-family: 'Ubuntu',sans-serif;
            }
            body{
                
                align-items: center;
                min-height: 100vh;
                background: #333;
            }
            table {
                border: 1px solid #151314;
                text-align: center;
                border-radius: 0px;
                background: linear-gradient(145deg,#2e2e2e,#373737);
                box-shadow: 9px 9px 18px #1f1f1f,
                        -9px -9px 18px #474747;
                    
            }
            th{
                border: 1px solid #151314;
                color: #00FFFF;
                text-align: center;
            }
            td{
                border: 1px solid #151314;
                color: #00FFFF;
                text-align: center;
            }
            table.center{
                margin-left: auto;
                margin-right: auto;
            }
        </style>
    </head>
    <body>
        <a href="timetable?lecturerid=${sessionScope.account.lecturerid}">Home</a>
        <input style="display: block" type="text" name="gid" value="${requestScope.gid}">
        <table class="center">
            <tr>
                <th></th>
                    <c:forEach items="${requestScope.sessions}" var="ses" varStatus="s">
                    <th >Slot ${s.count}</th>
                    </c:forEach>
                <th>Attendence Report</th>
            </tr>

            <c:forEach items="${requestScope.students}" var="students">
                <tr>
                    <c:set var="attended" value="0"/>
                    <c:set var="total" value="0"/>
                    <td>${students.sid} - ${students.sname}</td>
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
