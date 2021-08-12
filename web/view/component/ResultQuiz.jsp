<%-- 
    Document   : StartQuiz
    Created on : May 29, 2021, 4:24:14 PM
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
        <title>Page</title>
    </head>
    <body>
        <form action="" method="POST">
            <table>
                <tbody>
                    <tr>
                        <td class="text-teal"><h3>Take another test</h3></td>
                        <td>
                            <input type="submit" value="Start" name="resetQuiz">
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
