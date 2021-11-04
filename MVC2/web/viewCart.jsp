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
        <link rel="stylesheet" href="base">
        <link rel="stylesheet" href="grid">
        <link rel="stylesheet" href="viewCartStyle">
    </head>
    <body>
        <div class="container grid">
            <c:set var="cart" value="${sessionScope.CART}" />

            <!-- NOT EMPTY CART -->
            <c:if test="${not empty cart}">
                <div class="row">
                    <h1>Your Cart</h1>
                </div>


                <c:set var="items" value="${cart.items}" />
                <!-- NOT EMPTY ITEMS -->
                <c:if test="${not empty items}">
                    <div class="result row">
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
                                                ${dto.description}
                                            </td>
                                            <td style="text-align: center">
                                                ${quantity}
                                            </td>
                                            <td>
                                                <fmt:formatNumber value="${dto.price}" 
                                                                  maxFractionDigits="0" />đ
                                            </td>
                                            <td style="text-align: center">
                                                <input type="checkbox" name="chkItem" 
                                                       value="${dto.SKU}" />
                                            </td>
                                            <td style="text-align: center">
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
                                            <input type="submit" class="btn"
                                                   value="Remove Selected Books" 
                                                   name="btAction" />
                                        </td>
                                        <td>
                                            <input type="submit" class="btn"
                                                   value="Check Out Selected Books"
                                                   name="btAction" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </c:if>

                <!-- EMPTY ITEMS -->
                <c:if test="${empty items}">
                    <div class="no-item row">
                        <h2>
                            No item exited in your cart!
                        </h2>
                        <a href="showBookAction">Add More Books to Your Cart</a>
                    </div>
                </c:if>
            </c:if>

            <!-- EMPTY CART -->
            <c:if test="${empty cart}">
                <div class="no-cart row">
                    <h1>No cart is existed!</h1>
                    <a href="showBookAction">Click Here To Go Shopping</a>
                </div>
            </c:if>
        </div>
    </body>
</html>
