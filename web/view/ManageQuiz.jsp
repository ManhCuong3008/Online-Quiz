<%-- 
    Document   : ManageQuiz
    Created on : May 29, 2021, 3:45:55 PM
    Author     : NguyenManhCuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/view/css/style.css"></c:url>">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Manage Quiz Page</title>
        </head>
        <body>
            <div id="container" style=" min-height: 412px;">
            <%@include file="component/Header.jsp" %>
            <div class="main" style="padding-left:38px; padding-top:10px;"> 
                <p class="text-teal" style="padding-bottom: 20px;">Number of questions: <span class="text-blue" >${totalItems}</span></p>
                <table class="table-manage">
                    <thead>
                    <th style="text-align: left; color: blue; padding: 0px;">Question</th>
                    <th style="text-align: left; color: blue;padding: 0px;">DateCreated</th>
                    </thead>
                    <tbody>
                        <c:if test="${not empty listQuestion}">
                            <c:forEach items="${listQuestion}" var="question">
                                <tr>
                                    <td style="width: 450px;padding-right: 50px;">${question.question}</td>
                                    <td>${question.getDateConvertToString()}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
                <center> 
                    <c:if test="${totalPage>1}">
                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <div class="paging" >
                                <a href="managequiz?index=${i}">${i}</a>
                            </div>
                        </c:forEach>
                    </c:if>
                </center>
            </div>
        </div>
    </body>
</html>
