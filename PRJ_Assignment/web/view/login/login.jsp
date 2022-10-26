<%-- 
    Document   : login
    Created on : Oct 23, 2022, 6:25:31 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login FAP</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="view/login/login.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@48,300,0,0" />
    </head>
    <body>

        <div  class="login-card-container">
            <c:if test="${requestScope.status eq 2}">
                <div class="alert alert-danger">
                    <strong>Danger!</strong> Your username or password is Invalid!
                </div>
            </c:if>
            <c:if test="${requestScope.status eq 1}">
                <div class="alert alert-success">
                    <strong>Success!</strong> Logout successfully!
                </div>
            </c:if>
            <div class="login-card">
                <div class="login-card-logo">
                    <img src="view/login/logo.jpg" alt="logo">
                </div>
                <div class="login-card-header">
                    <h1>Sign in</h1>
                    <div>Please login to use platform</div>
                </div>
                <form  class="login-card-form" action="login" method="POST">
                    <div class="form-item">
                        <span class="form-item-icon material-symbols-rounded">mail</span>
                        <input type="text" placeholder="Enter Email" required autofocus name="username" >
                    </div>
                    <div class="form-item">
                        <span class="form-item-icon material-symbols-rounded">lock</span>
                        <input type="password" placeholder="Enter Password" required name="password" >
                    </div>
                    <div class="form-item-other">
                        <div class="checkbox">
                            <input type="checkbox" name="rememberMeCheckbox" >
                            <label for="rememberMeCheckbox">Remember Me</label>
                        </div>
                        <a href="#">I forgot my password!</a>
                    </div>
                    <button type="submit">Sign In</button>
                </form>
                <div class="login-card-footer">
                    Don't have an account? <a href="#">Create a free account</a>
                </div>
            </div>
        </div>
    </body>
</html>
