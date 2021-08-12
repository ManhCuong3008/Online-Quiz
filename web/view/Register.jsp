<%-- 
    Document   : Register
    Created on : May 28, 2021, 3:59:04 PM
    Author     : NguyenManhCuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="<c:url value="/view/css/style.css"></c:url>">
         <link rel="stylesheet" href="<c:url value="/view/css/home.css"></c:url>">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register Page</title>
    </head>
    <body>
        <div id="container">
           <%@include file="component/Header.jsp" %>
        <div class="main">
                 <c:if test="${not empty message}">
                    <b class="${alert}">${message}</b>
                </c:if>
           <form action="#"method="POST">
                <table class="register-form">
                    <tr>
                        <th>
                            <h3>Registration Form</h3>
                        </th>
                        <td></td>
                    </tr>
                    <tr>
                        <td><label class="text-teal " for="username">User Name:</label></td>
                        <td><input class="input_ mb-5" type="text" id="username" name="username"></td>
                    </tr>
                    <tr>
                        <td><label class="text-teal" for="password">Password:</label></td>
                        <td><input class="input_" type="password" id="password" name="password"></td>
                    </tr>
                    <tr>
                        <td><label class="text-teal " for="user_type">User Type:</label></td>
                        <td>
                            <select name="roleid" id="user_type">
                                <option value="1">Teacher</option>
                                <option value="2">Student</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label class="text-teal" for="email">Email:</label></td>
                        <td><input class="input_" type="email" id="email" name="email"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Register"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    </body>
</html>
