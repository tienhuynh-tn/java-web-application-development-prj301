<%-- 
    Document   : confirmCheckOut
    Created on : Oct 20, 2021, 4:12:10 PM
    Author     : Huynh Le Thuy Tien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Check Out</title>
    </head>
    <body>
        <c:set var="selectedItems" value="${paramValues.chkCheckOut}"/>
        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}" />
            <c:if test="${not empty items}">
                <c:set var="list" value="${sessionScope.CHECK_OUT_ITEMS}" />
                <c:if test="${not empty list}">
                    <form action="DispatchServlet">
                        <h1>Check Out Your Cart</h1>
                        Name <span style="color: red">*</span> <input type="text" name="txtName" value="" required/> <br/>
                        Address <span style="color: red">*</span> <input type="text" name="txtAdress" value="" required/> <br/>
                        <input type="submit" value="Check Out" name="btAction" />
                        
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
                                <c:set var="total" />
                                <c:forEach var="item" items="${list}" varStatus="counter">
                                    <c:set var="dto" value="${item.key}" />
                                    <c:set var="quantity" value="${item.value}" />
                                    <c:set var="price" value="${dto.price}" />
                                    <c:set var="total" value="${quantity * price}" />
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
                                            ${quantity}
                                        </td>
                                        <td>
                                            ${price}
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="4" style="text-align: right">
                                        Total
                                    </td>
                                    <td>
                                        ${total}
                                        <input type="hidden" name="txtTotal" value="${total}" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <a href="DispatchServlet?btAction=View Your Cart">Go Back To Your Cart</a> 
                    </form>
                </c:if>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2>No item is selected to check out!!!</h2>
            <a href="DispatchServlet?btAction=View Your Cart">Go Back To Your Cart</a>
        </c:if>
    </body>
</html>
