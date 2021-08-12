<%-- 
    Document   : MakeQuiz
    Created on : May 28, 2021, 5:44:52 PM
    Author     : NguyenManhCuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/view/css/style.css"></c:url>">
        <link rel="stylesheet" href="<c:url value="/view/css/make-quiz.css"></c:url>">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Make Quiz Page</title>
        </head>
        <body>
            <div id="container">
            <%@include file="component/Header.jsp" %>
            <div class="main">
                <c:if test="${not empty message}">
                    <b class="${alert}">${message}</b>
                </c:if>
                    <form action="#" method="POST">
                    <table class="makeQuiz-form">
                        <tbody>
                            <tr>
                                <td>Question:</td>
                                <td><textarea name="question" id="" cols="80" rows="6"></textarea></td>
                            </tr>
                            <tr>
                                <td>Option1:</td>
                                <td><textarea name="option1" id="" cols="80" rows="3"></textarea></td>
                            </tr>
                            <tr>
                                <td>Option2:</td>
                                <td><textarea name="option2" id="" cols="80" rows="3"></textarea></td>
                            </tr>
                            <tr>
                                <td>Option3:</td>
                                <td><textarea name="option3" id="" cols="80" rows="3"></textarea></td>
                            </tr>
                            <tr>
                                <td>Option4:</td>
                                <td><textarea name="option4" id="" cols="80" rows="3"></textarea></td>
                            </tr>
                            <tr>
                                <td>Answer(s):</td>
                                <td>
                                    <input type="checkbox" name="answers" id="Option1" value="1">
                                    <label for="Option1">Option 1  </label>
                                    <input type="checkbox" name="answers" id="Option2" value="2">
                                    <label for="Option2">Option 2  </label>
                                    <input type="checkbox" name="answers" id="Option3" value="3">
                                    <label for="Option3">Option 3  </label>
                                    <input type="checkbox" name="answers" id="Option4" value="4">
                                    <label for="Option4">Option 4  </label>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="submit" name="submit" id="submit" value="Save"></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
