<%-- 
    Document   : Home
    Created on : May 27, 2021, 6:42:29 PM
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
        <title>Home Page</title>
    </head>
    <body>
       <div id="container">
           <%@include file="component/Header.jsp" %>
        <div class="main"> 
            <c:if test="${not empty message}">
                    <p class="${alert}">${message}</p>
            </c:if>
            <form action="#" method="POST">
                <table class="form-login">
                    <tr>
                        <th>
                            <h3>Logic Form</h3>
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
                        <td></td>
                        <td>
                            <input type="submit" value="Sign in">
                            <a class="text-teal" href="<c:url value="/register"></c:url>">Register</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    </body>
</html>
