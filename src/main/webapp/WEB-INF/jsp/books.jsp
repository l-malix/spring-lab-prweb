<%-- 
    Document   : books
    Created on : 21 mars 2022, 18:57:16
    Author     : lmalix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <tr id="header">
                <th>Book #</th>
                <th class="name">Title</th>
                <th class="name">Authors</th>
                <th></th>
            </tr>
            <c:forEach var="book" items="${books}">
                <tr id="row-1">
                    <td>${book.bookId}</th>
                    <td>${book.bookTitle}</th>
                    <td>${book.bookAuthors}</th>
                    <td class="icons-col">
                        <div class="icons-row">
                            <form action="#" method="POST">
                                <input type="hidden" name="id" value="${book.bookId}">
                                <button name="edit" formaction="editBook.do"><img src="img/edit.png" /></button>
                                <button name="delete" formaction="delBook.do"><img src="img/delete.png" /></button>
                            </form>
                        </div>
                        </th>
                </tr>

            </c:forEach>

            <tr>
                <td colspan="3"></td>
                <td class="icons-col">
                    <form  method="POST">
                        <div action="#" class="icons-row">
                            <button formaction="addBook.do"><img src="img/plus.png" /></button>
                        </div>
                    </form>

                </td>
            </tr>
        </table>
        <form action="toPersons.do" method="POST"><button>Switch to persons</button></form>
    </body>
</html>
