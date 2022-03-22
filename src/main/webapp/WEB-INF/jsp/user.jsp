<%-- 
    Document   : user
    Created on : 21 mars 2022, 05:53:12
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
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="js/main.js" type="text/javascript"></script>
        <title>Document</title>
    </head>
    <body>
        <form action="saveUser.do" method="POST">
            <table class="user">
                <tr>
                    <th>User #</th>
                    <td>
                        <c:choose>
                            <c:when test="${empty person.personId}">
                                NEW
                                <input type="hidden" name="id" value="-1">
                            </c:when>
                            <c:otherwise>
                                <input
                                    type="text"
                                    name="id"
                                    id="personId"
                                    placeholder="id"
                                    value ="${person.personId}"
                                    />
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>First name</th>
                    <td>
                        <input
                            type="text"
                            name="firstname"
                            id="first-name"
                            placeholder="First name"
                            value ="${person.personFirstname}"
                            />
                    </td>
                </tr>
                <tr>
                    <th>Last name</th>
                    <td>
                        <input
                            type="text"
                            name="lastname"
                            id="last-name"
                            placeholder="Last name"
                            value ="${person.personLastname}"
                            />
                    </td>
                </tr>
                <tr>
                    <th>Birth date</th>
                    <td>
                        <input type="text" 
                               name="dob" 
                               id="dob" 
                               placeholder="Date of birth"
                               value ="<fmt:formatDate value="${person.personBirthdate}" pattern="yyyy-MM-dd" />"/>
                    </td>
                </tr>
            </table>
            <button type="submit"> Save </button>
        </form>

        <table>
            <tr>
                <th>Date</th>
                <th class="name">Titles</th>
                <th>Return</th>
            </tr>

            <c:forEach var="borrow" items="${person.borrowCollection}">
                <tr>

                    <td><fmt:formatDate value="${borrow.borrowDate}" pattern="yyyy-MM-dd" /></td>
                    <td>${borrow.bookId.bookTitle}</td>
                    <td class="icons-col">
                        <c:choose>


                            <c:when test="${not empty borrow.borrowReturn}">
                                <fmt:formatDate value="${borrow.borrowReturn}" pattern="yyyy-MM-dd" />

                            </c:when>
                            <c:otherwise>
                                <div class="icons-row">
                                    <button onclick="returnBorrow(this, ${borrow.borrowId}); return false;"><img src="img/return.png" /></button>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>


            <tr>
            <form action="addBorrowedBook.do" method="POST">
                <td>
                    <select name="bookId">
                        <option value="-1" selected="selected">-</option>
                        <c:forEach var="book" items="${books}">
                            <option value="${book.bookId}" >${book.bookTitle}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="id" value="${person.personId}" />

                </td>
                <td class="icons-col">
                    <div class="icons-row">
                        <button type="submit"><img src="img/plus.png" /></button>
                    </div>
                </td>
            </form>

        </tr>

    </table>


</body>
</html>

