<%-- 
    Document   : Header
    Created on : May 28, 2021, 3:09:15 PM
    Author     : NguyenManhCuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/view/css/style.css"></c:url>">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Header Page</title>
        </head>
        <body>
            <div class="header">
                <div class="header_bg"></div>
                <div class="header_nav">
                    <ul class="menu">
                        <li><a href="<c:url value="/home"></c:url>">Home</a></li>
                        <c:if test="${not empty USERMODEL}">
                        <li><a href="<c:url value="/takequiz"></c:url>">Take Quiz </a></li>
                            <c:if test="${USERMODEL.roleID==1}">
                            <li><a href="<c:url value="/makequiz"></c:url>">Make Quiz </a></li>
                            <li><a href="<c:url value="/managequiz"></c:url>">Manage Quiz </a></li>
                            </c:if>
                        <li><a href="<c:url value="/logout"></c:url>">Logout</a></li>
                        </c:if>


                </ul>
            </div>
        </div>
    </body>
</html>
