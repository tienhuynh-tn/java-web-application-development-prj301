<%-- 
    Document   : confirmCheckOut
    Created on : Oct 20, 2021, 4:12:10 PM
    Author     : Huynh Le Thuy Tien
--%>

<%--<%@page import="java.math.BigDecimal"%>
<%@page import="tienhlt.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="tienhlt.cart.CartObject"%>--%>
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
        
        <%--<%
        String[] selectedItem = request.getParameterValues("chkCheckOut");
        for (String select : selectedItem) {
            System.out.println(select);
        }
        if (session != null) {
            CartObject cart = (CartObject)session.getAttribute("CART");
            if (cart != null) {
                Map<ProductDTO, Integer> items = cart.getItems();
                if (items != null) {
                    Map<ProductDTO, Integer> list = 
                            (Map<ProductDTO, Integer>)session.getAttribute("CHECK_OUT_ITEMS");
                    if (list != null) {
                        %>
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
                                        <th>Description</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    int count = 0;
                                    BigDecimal total = new BigDecimal(0);
                                    for (ProductDTO dto : list.keySet()) {
                                        int quantity = list.get(dto);
                                        BigDecimal price = dto.getPrice();
                                        for (String itemSelectedCheckOut : selectedItem) {
                                            if (dto.getSKU() == itemSelectedCheckOut) {
                                                total = total.add(price.multiply(new BigDecimal(quantity)));
                                                %>
                                                <tr>
                                                    <td>
                                                        <%= ++count %>
                                                    </td>
                                                    <td>
                                                        <%= dto.getSKU() %>
                                                    </td>
                                                    <td>
                                                        <%= dto.getName() %>
                                                    </td>
                                                    <td>
                                                        <%= dto.getDescription() %>
                                                    </td>
                                                    <td>
                                                        <%= quantity %>
                                                    </td>
                                                    <td>
                                                        <%= price %>
                                                    </td>
                                                </tr>
                                    <%
                                            }
                                        }
                                    }
                                    %>
                                    <tr>
                                        <td colspan="5" style="text-align: right">
                                            Total
                                        </td>
                                        <td>
                                            <%= total %>
                                            <input type="hidden" name="txtTotal" value="<%= total %>" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                        <a href="DispatchServlet?btAction=View Your Cart">Go Back To Your Cart</a> 
            <%   
                    } return;
                } 
            }
        }
        %>
        <h2>No item is selected to check out!!!</h2>
        <a href="DispatchServlet?btAction=View Your Cart">Go Back To Your Cart</a>
        --%>
    </body>
</html>
