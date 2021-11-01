<%-- 
    Document   : shopping
    Created on : Oct 18, 2021, 8:33:58 PM
    Author     : Huynh Le Thuy Tien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/grid.css">
        <link rel="stylesheet" href="./css/shoppingStyle.css">
    </head>
    <body>
        <div class="container grid">
            <div class="row">
                <h1>Welcome to PRJ301 Book Store</h1>
            </div>

            <div class="result row">
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
                                <tr>
                                    <td style="text-align: center">
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${dto.SKU}
                                    </td>
                                    <td>
                                        ${dto.name}
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${dto.price}" maxFractionDigits="0"/> đ
                                    </td>
                                    <td>
                                        ${dto.description}
                                    </td>
                                    <td style="text-align: center">
                                        <c:set var="quantity" value="${dto.quantity}" />
                                        <c:set var="cart" value="${sessionScope.CART}" />
                                        <c:set var="remainQuantity" value="${quantity - cart.getQuantityBySKU(dto.SKU)}" /> 
                                        ${remainQuantity}
                                    </td>
                                    <td>
                                        <c:if test="${quantity eq 0}">
                                            <font color="red">This book is sold out</font>
                                        </c:if>

                                        <c:if test="${quantity ne 0}">
                                            <form action="addBookToCartAction">
                                                <input type="submit" value="Add" class="btn"
                                                       <c:if test="${remainQuantity eq 0}">
                                                           style="visibility: hidden"
                                                       </c:if>
                                                       />
                                                <input type="hidden" name="pk" value="${dto.SKU}" />
                                            </form>
                                        </c:if>
                                        <%--
                                            <c:url var="urlRewriting" value="addBookToCartAction">
                                                <c:param name="btAction" value="Add Book to Your Cart"/>
                                                <c:param name="pk" value="${dto.SKU}" />
                                            </c:url>
                                            <c:if test="${quantity ne 0}">
                                                <a href="${urlRewriting}"
                                                    <c:if test="${remainQuantity eq 0}">
                                                       disabled="disabled" style="cursor: no-drop"
                                                    </c:if> >
                                                    Add
                                                </a>
                                            </c:if>
                                        --%>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <form action="viewCartPage">
                    <input type="submit" value="View Your Cart" class="btn"/>
                </form>
            </div>

            <div class="no-book row">
                <c:if test="${empty result}">
                    <h2>No book is in store!</h2>
                </c:if>
            </div>
        </div>
    </body>
</html>