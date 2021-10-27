<%-- 
    Document   : shopping
    Created on : Oct 18, 2021, 8:33:58 PM
    Author     : Huynh Le Thuy Tien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Welcome to PRJ301 Book Store</h1>
        <c:set var="result" value="${requestScope.BOOK_LIST}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>SKU</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Add to Cart</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                        <c:url var="urlRewriting" value="DispatchServlet">
                            <c:param name="btAction" value="Add Book to Your Cart"/>
                            <c:param name="pk" value="${dto.SKU}" />
                        </c:url>
                        <form action="DispatchServlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.SKU}
                                </td>
                                <td>
                                    ${dto.name}
                                </td>
                                <td>
                                    ${dto.price}
                                </td>
                                <td>
                                    ${dto.description}
                                </td>
                                <td>
                                    ${dto.quantity}
                                </td>
                                <td>
                                    <a href="${urlRewriting}">Add</a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <form action="DispatchServlet">
                <input type="submit" value="View Your Cart" name="btAction" />
            </form>
        </c:if>
        <c:if test="${empty result}">
            <h2>No book is in store</h2>
        </c:if>
    </body>
</html>