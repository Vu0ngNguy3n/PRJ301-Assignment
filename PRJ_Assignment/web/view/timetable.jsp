<%-- 
    Document   : attend
    Created on : Oct 14, 2022, 9:34:43 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FAP Attend</title>
        <script >
            function getlist(String x) {
                window.location.href = 'timetable?lecturerid=' + x;
            }
        </script>
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
        <div align="center">
        Lecturer: 
        <select >
            <c:forEach items="${requestScope.lecturer}" var="l">
                <option value="${l.lecturerid}" name="lecturerid" onchange="getlist(${l.lecturerid})">${l.lecturername}</option>
            </c:forEach>
            
        </select>
        
    </div><br><br>
    <table border="1px">
        <thead style="background-color: #0fcc45;">
            <tr>

                <th rowspan="2" align="left">
                    
                    <br>Time From: <input type="date"><br>
                        Time To: <input type="date" name="" id="" style="margin-top:5px; ;" >
                </th>

                <th align="center">Mon</th><th align="center">Tue</th><th align="center">Wed</th><th align="center">Thu</th><th align="center">Fri</th><th align="center">Sat</th><th align="center">Sun</th>
            </tr>
            <tr>
                <th align="center">10/10</th><th align="center">11/10</th><th align="center">12/10</th><th align="center">13/10</th><th align="center">14/10</th><th align="center">15/10</th><th align="center">16/10</th>
                
            </tr>
        </thead>
        <tbody>
           
            <tr><td>Slot 1 </td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
            <tr><td>Slot 2 </td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr></tr>
            <tr><td>Slot 3</td> <td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
            <tr><td>Slot 4 </td> <td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
            <tr><td>Slot 5 </td> <td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
            <tr><td>Slot 6 </td> <td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
            

          
        </tbody>
    </table>
    </body>
</html>
