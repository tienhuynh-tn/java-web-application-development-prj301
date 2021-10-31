<%-- 
    Document   : confirmDelete
    Created on : Oct 29, 2021, 10:47:47 PM
    Author     : Huynh Le Thuy Tien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Delete</title>
    </head>
    <body>
        <h1>Confirm The Profile of User Will Be Deleted</h1>
        <c:set var="dto" value="${sessionScope.SHOW_PROFILE}" />
        Username: ${dto.username} <br/>
        Password: ${dto.password} <br/>
        Full Name: ${dto.lastname} <br/>
        Role: 
        <c:if test="${dto.role eq true}">
            Admin
        </c:if>
        <c:if test="${dto.role eq false}">
            Not Admin
        </c:if>
        <br/>
        <form action="deleteAction">
            <input type="submit" value="Confirm" />
            <input type="hidden" name="pk" value="${dto.username}" />
            <input type="hidden" name="lastSearchValue" value="${param.lastSearchValue}" />
        </form>
        <c:url var="urlReWriting" value="searchAction">
            <c:param name="txtSearchValue" value="${param.lastSearchValue}" />
        </c:url>
        <a href="${urlReWriting}">
            <input type="submit" value="Cancel"/>
        </a>
    </body>
</html>
