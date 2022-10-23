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

            tr:nth-child(even) {
                background-color: #dddddd;
            }
        </style>
        <script>
            function getTimeTable(id) {
                 var result = confirm("Are you sure return to TimeTable?");
                if (result) {
                    
                    window.location.href('timetable?lecturerid=' + id);
                }
            }
        </script>
    </head>
    <body>
       <a href="timetable?lecturerid=${requestScope.lecturerid}">Return</a>
            
       <a href="listattend?gid=${requestScope.gid}&lecturerid=${requestScope.lecturerid}">Attend Of Class</a>
        <h2>Attendence </h2>
        <form action="attend" method="POST">
            Lecturer: <input type="text" name="lecturerid" value="${requestScope.lecturerid}">
            <input type="hidden" name="sessionid" value="${requestScope.sessionid}">
            <input type="hidden" name="gid" value="${requestScope.gid}">
           
            <table>
                <tr>
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
            
            <input type="submit" value="Update">
        </form>
    </body>
</html>
