<%-- 
    Document   : viewCart
    Created on : Oct 31, 2021, 2:32:14 PM
    Author     : Huynh Le Thuy Tien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <c:set var="cart" value="${sessionScope.CART}" />

        <!-- NOT EMPTY CART -->
        <c:if test="${not empty cart}">
            <h1>Your Cart</h1>

            <c:set var="items" value="${cart.items}" />
            <!-- NOT EMPTY ITEMS -->
            <c:if test="${not empty items}">
                <form action="cartAction">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>SKU</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Remove</th>
                                <th>Check Out</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <c:set var="dto" value="${item.key}" />
                                <c:set var="quantity" value="${item.value}" />
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
                                        ${dto.description}
                                    </td>
                                    <td>
                                        ${quantity}
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${dto.price}" 
                                                          maxFractionDigits="0" />đ
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" 
                                               value="${dto.SKU}" />
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkCheckOut" 
                                               value="${dto.SKU}" />
                                    </td>
                                </tr>
                            </c:forEach>
                                <tr>
                                    <td colspan="6">
                                        <a href="showBookAction">
                                            Add More Books to Your Cart
                                        </a>
                                    </td>
                                    <td>
                                        <input type="submit" 
                                               value="Remove Selected Books" 
                                               name="btAction" />
                                    </td>
                                    <td>
                                        <input type="submit" 
                                               value="Check Out Selected Books"
                                               name="btAction" />
                                    </td>
                                </tr>
                        </tbody>
                    </table>
                </form>
            </c:if>

            <!-- EMPTY ITEMS -->
            <c:if test="${empty items}">
                <h2>
                    No item exited in your cart!
                </h2>
                <a href="showBookAction">Add More Books to Your Cart</a>
            </c:if>
        </c:if>

        <!-- EMPTY CART -->
        <c:if test="${empty cart}">
            <h1>No cart is existed!</h1>
            <a href="showBookAction">Click Here To Go Shopping</a>
        </c:if>
    </body>
</html>
