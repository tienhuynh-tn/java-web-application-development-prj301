<%-- 
    Document   : viewCart
    Created on : Oct 14, 2021, 11:41:28 AM
    Author     : Huynh Le Thuy Tien
--%>

<%--<%@page import="tienhlt.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="tienhlt.cart.CartObject"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>SKU</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Action</th>
                                <th>Check Out</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <c:set var="dto" value="${item.key}"/>
                                <c:set var="quantity" value="${item.value}"/>
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
                                        ${dto.price}
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
                                    <a href="DispatchServlet?btAction=Add Book to Your Cart">Add More Books to Your Cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Book" name="btAction" />
                                </td>
                                <td>
                                    <input type="submit" value="Check Out Selected Book" name="btAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2>
                No cart is existed!!!
            </h2>
        </c:if>



        <%--<h1>Your Cart Items</h1>
        <%
            //1. Cust goes to his/her cart place
            if (session != null) {
                //2. Cust takes his/her car
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets all items
//                    Map<String, Integer> items = cart.getItems();
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
        %>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>SKU</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Check Out</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <form action="DispatchServlet">
                                <tbody>
                                    <%
                                        int count = 0;
                                        for (ProductDTO dto : items.keySet()) {
                                    %>
                                    <tr>
                                        <th>
                                            <%= ++count%>
                                        </th>
                                        <th>
                                            <%= dto.getSKU()%>
                                        </th>
                                        <th>
                                            <%= dto.getName()%>
                                        </th>
                                        <th>
                                            <%= dto.getDescription()%>
                                        </th>
                                        <th>
                                            <%= items.get(dto)%>
                                        </th>
                                        <th>
                                            <%= dto.getPrice()%>
                                        </th>
                                        <th>
                                            <input type="checkbox" name="chkCheckOut" 
                                                   value="<%= dto.getSKU() %>" />
                                        </th>
                                        <th>
                                            <input type="checkbox" name="chkItem" 
                                                   value="<%= dto.getSKU()%>" />
                                        </th>
                                    </tr>
                                    <%
                                        }
                                    %>
                                    <tr>
                                        <td colspan="6">
                                            <a href="DispatchServlet?btAction=Add Book to Your Cart">Add More Books to Your Cart</a>
                                        </td>
                                        <td>
                                            <input type="submit" value="Check Out Selected Book" name="btAction" />
                                        </td>
                                        <td>
                                            <input type="submit" value="Remove Selected Book" name="btAction" />
                                        </td>
                                    </tr>
                                </tbody>
                            </form>
                        </table>
        <%
                    } //items had existed 
                    return;
                }//cart had existed
            }//cart place must exist
            
%>
        <h2>
            No cart existed!
        </h2>
        <a href="DispatchServlet?btAction=Buy">Click Here To Go Shopping</a>
        --%>
    </body>
</html>
