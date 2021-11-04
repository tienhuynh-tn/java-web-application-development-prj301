<%-- 
    Document   : confirmCheckOut
    Created on : Oct 20, 2021, 4:12:10 PM
    Author     : Huynh Le Thuy Tien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Check Out</title>
        <link rel="stylesheet" href="base">
        <link rel="stylesheet" href="grid">
        <link rel="stylesheet" href="confirmCheckOutStyle">
    </head>
    <body>
        <div class="container grid">
            <c:set var="list" value="${sessionScope.CHECK_OUT_ITEMS}" />

            <!-- NOT EMPTY LIST OF SELECTED ITEMS FOR CHECK-OUT -->
            <c:if test="${not empty list}">
                <div class="main row">
                    <form action="checkOutAction">
                        <h1>Check Out Your Cart</h1>
                        <div class="user-info">
                            <div class="item">
                                <div class="title">
                                    Name *
                                </div>
                                <div class="box">
                                    <input type="text" name="txtName" value=""
                                       class="text-box" required/> 
                                </div>
                            </div>
                            <div class="item">
                                <div class="title">
                                    Address *
                                </div>
                                <div class="box">
                                    <input type="text" name="txtAdress" value="" 
                                       class="text-box" required/> 
                                </div>
                            </div>
                            <input type="submit" value="Check Out" class="btn" />
                        </div>

                        <div class="result">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>SKU</th>
                                        <th>Name</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${list}" varStatus="counter">
                                        <c:set var="dto" value="${item.key}" />
                                        <c:set var="quantity" value="${item.value}" />
                                        <c:set var="price" value="${dto.price}" />
                                        <c:set var="total" value="${total + quantity * price}" />
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
                                            <td style="text-align: center">
                                                ${quantity}
                                            </td>
                                            <td>
                                                <fmt:formatNumber value="${price}" 
                                                                  maxFractionDigits="0"/>đ
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="4" style="text-align: right">
                                            Total
                                        </td>
                                        <td>
                                            <fmt:formatNumber value="${total}" maxFractionDigits="0"/>đ
                                            <input type="hidden" name="txtTotal" value="${total}" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </form>    
                </div>
            </c:if>

            <!-- EMPTY LIST OF SELECTED ITEMS FOR CHECK-OUT -->
            <div class="no-list row">
                <c:if test="${empty list}">
                    <h2>No Selected Item for CheckOut!</h2>
                    <a href="viewCartPage">Go Back To Your Cart</a> 
                </c:if>
            </div>
        </div>
    </body>
</html>
