<%-- 
    Document   : users
    Created on : 21 mars 2022, 04:41:18
    Author     : lmalix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="css/main.css" type="text/css" rel="stylesheet" />

        <title>Document</title>
    </head>
    <body>
        <table>
            <tr>
                <th>User #</th>
                <th class="name">First name</th>
                <th class="name">Last name</th>
                <th>Birthdate</th>
                <th></th>
            </tr>
            <c:forEach var="user" items="${listPerson}">
                <tr>
                    <td>${user.personId}</td>
                    <td>${user.personFirstname}</td>
                    <td>${user.personLastname}</td>
                    <td><fmt:formatDate value="${user.personBirthdate}" pattern="yyyy-MM-dd" /></td>
                    <td class="icons-col">
                        <form action="#" method="POST">
                            <div class="icons-row">
                                <input type="hidden" name="personId" value="${user.personId}">
                                <button name="edit" formaction="editUser.do"><img src="img/edit.png" /></button>
                                <button name="delete" formaction="delUser.do"><img src="img/delete.png" /></button>
                            </div>
                        </form>

                        </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4"></td>
                <td class="icons-col">
                    <form action="#" method="POST">
                    <div class="icons-row">
                        <button name="plus" formaction="addUser.do"><img src="img/plus.png" /></button>
                    </div>
                    </form>
                </td>
            </tr>
        </table>
        <form action="toBooks.do" method="POST"><button>Switch to books</button></form>
    </body>
</html>
