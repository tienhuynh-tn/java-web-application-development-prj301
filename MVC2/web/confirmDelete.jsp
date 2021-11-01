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
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/grid.css">
        <link rel="stylesheet" href="./css/confirmDeleteStyle.css">
    </head>
    <body>
        <div class="container grid">
            <div class="row">
                <h1>Confirm Delete</h1>
            </div>

            <div class="profile row">
                <c:set var="dto" value="${sessionScope.SHOW_PROFILE}" />
                <c:if test="${not empty dto}">
                    <div class="info">
                        <div>
                            Username: ${dto.username}
                        </div>
                        <div>
                            Password: ${dto.password} 
                        </div>
                        <div>
                            Full Name: ${dto.lastname}
                        </div>
                        <div>
                            Role: 
                            <c:if test="${dto.role eq true}">
                                Admin
                            </c:if>
                            <c:if test="${dto.role eq false}">
                                Not Admin
                            </c:if>
                        </div>
                    </div>
                    <div>
                        <img src="./img/avatar.jpg" alt="Avatar">
                    </div>
                </c:if>
            </div>

            <div class="button">
                <form action="deleteAction">
                    <input type="submit" value="Confirm" class="btn" />
                    <input type="hidden" name="pk" value="${dto.username}" />
                    <input type="hidden" name="lastSearchValue" value="${param.lastSearchValue}" />
                </form>
                <c:url var="urlReWriting" value="searchAction">
                    <c:param name="txtSearchValue" value="${param.lastSearchValue}" />
                </c:url>
                <a href="${urlReWriting}">
                    <input type="submit" value="Cancel" class="cancel"/>
                </a>
            </div>
        </div>
    </body>
</html>
