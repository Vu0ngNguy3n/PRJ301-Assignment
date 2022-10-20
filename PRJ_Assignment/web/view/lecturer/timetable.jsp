<%-- 
    Document   : attend
    Created on : Oct 14, 2022, 9:34:43 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="helper" class="helper.DateTimeHelper"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FAP TimeTable</title>

    </head>
    <body>
        <style>
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

        </style>
        <form action="timetable" method="GET">
            <div align="center">
                Lecturer: <input type="text" name="lecturerid" value="${param.lecturerid}">
                
                <br/>

                From: <input type="date" name="from" value="${requestScope.from}">
                To: <input type="date" name="to" value="${requestScope.to}"}>
                <input type="submit" value="View">
            </div><br><br>
        </form> 
        <table border="1px">
            <thead style="background-color: #0fcc45;">
                <tr>

                    <th rowspan="2" align="left">

                        <br>Time From: <input type="date"><br>
                        Time To: <input type="date" name="" id="" style="margin-top:5px; ;" >
                    </th>
                    <c:forEach items="${requestScope.datelist}" var="datelist">
                        <th align="center"> ${datelist}</th>
                        </c:forEach>

                </tr>
                <tr >
                    <c:forEach items="${requestScope.datelist}" var="datelist">
                        <th align="center">${helper.getDayNameofWeek(datelist)}</th>
                        </c:forEach>


                </tr>
            </thead>
            <tbody>

                <c:forEach items="${requestScope.slots}" var="s">
                    <tr>
                        <td text-align="center">Slot ${s.slot}<br/> ${s.time}</td>
                            <c:forEach items="${requestScope.datelist}" var="datelist">
                            <td>
                                <c:set var="num" value="0">
                                    
                                </c:set>
                                <c:forEach items="${requestScope.sessions}" var="session">
                                    <c:if test="${helper.compare(datelist,session.date) eq 0 and (session.slot.slot eq s.slot)}">
                                        <c:set var="num" value="1"/>
                                        <a href="attend?sessionid=${session.sessionid}&lecturerid=${param.lecturerid}">${session.group.getGid()}</a><br/>
                                        at ${session.room.room} <br/>
                                        <c:if test="${session.status eq true}">
                                            (Attend)
                                        </c:if>
                                        <c:if test="${session.status eq false}">
                                            (Absent)
                                        </c:if>
                                        <c:if test="${session.status eq null}">
                                            (not yet)
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







    </body>
</html>
