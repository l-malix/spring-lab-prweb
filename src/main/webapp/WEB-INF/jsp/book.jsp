<%-- 
    Document   : book
    Created on : 21 mars 2022, 19:22:58
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
        <form action="saveBook.do", method="POST">
            <table class="user">
                <tr>
                    <th>Book #</th>
                    <td>
                        <c:choose>
                            <c:when test="${empty book.bookId}">
                                NEW
                                <input type="hidden" name="id" value="-1">
                            </c:when>
                            <c:otherwise>
                                ${book.bookId}
                                <input
                                    type="hidden"
                                    name="id"
                                    id="id"
                                    placeholder="id"
                                    value ="${book.bookId}"
                                    /></td>
                            </c:otherwise>
                        </c:choose>
                </tr>
                <tr>
                    <th>Title</th>
                    <td>
                        <input
                            type="text"
                            name="title"
                            id="title"
                            placeholder="Title"
                            value ="${book.bookTitle}"
                            />
                    </td>
                </tr>
                <tr>
                    <th>Authors</th>
                    <td>
                        <input
                            type="text"
                            name="authors"
                            id="authors"
                            placeholder="Authors"
                            value="${book.bookAuthors}"
                            />
                    </td>

                </tr>

                <tr>
                    <td colspan="2">
                        <button type="submit">Save</button>
                    </td>
                </tr>
            </table>


        </form>
    </body>
</html>
