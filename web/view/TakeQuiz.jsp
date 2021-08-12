<%-- 
    Document   : TakeQuiz
    Created on : May 29, 2021, 3:50:41 PM
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
            <title>Take Quiz Page</title>
        </head>
        <body>
            <div id="container">
            <%@include file="component/Header.jsp" %>
            <div class="main">
                <c:if test="${not empty message}">
                    <b class="${alert}">${message}</b>
                </c:if>
                <c:if test="${empty QUIZMODEL and empty SCORE}">
                    <h3 class="">Welcome <span style="color:blue;">${USERMODEL.username}</span></h3>
                    <%@include  file="component/SelectQuiz.jsp" %>
                </c:if>   
                <c:if test="${not empty QUIZMODEL}">
                <h3>Welcome <span class="text-blue">${USERMODEL.username}</span></h3>
                    <%@include  file="component/StartQuiz.jsp" %>
                </c:if>
                <c:if test="${not empty SCORE}">
                    <h3>Your Score: <span class="text-blue">${SCORE} (${SCORE*10}%) - <c:if test="${SCORE < 5}">Not Pass</c:if><c:if test="${SCORE >= 5}">Passed</c:if></span></h3>
                    <%@include  file="component/ResultQuiz.jsp" %>
                </c:if>
            </div>
        </div>
    </body>
</html>
