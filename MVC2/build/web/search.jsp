<%-- 
    Document   : search
    Created on : Oct 5, 2021, 11:37:23 AM
    Author     : Huỳnh Lê Thủy Tiên <tien.huynhlt.tn@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/grid.css">
        <link rel="stylesheet" href="./css/searchStyle.css">
    </head>
    <body>
        <div class="container grid">
            <div class="header row">
                <div class="welcome">
                    Welcome, ${sessionScope.FULL_NAME}
                </div>
                <a href="logoutAction" class="logout">Log Out</a>
            </div>

            <c:set var="properties" value="${applicationScope.SITE_MAP}" />
            <c:set var="adminUserName" value="${properties.getProperty('admin')}" />

            <c:set var="isAdmin" value="${sessionScope.ADMIN}" />

            <!-- IF ADMIN TRUE -->
            <c:if test="${not empty isAdmin}">
                <div class="search row">
                    <h1>Search Account</h1>
                    <form action="searchAction">
                        <div class="search-content">
                            Search Value
                        </div> 
                        <input type="text" name="txtSearchValue" 
                               value="${param.txtSearchValue}" class="text-box search-box" /> 
                        <input type="submit" value="Search" class="btn" />
                    </form> 
                </div>


                <c:set var="searchValue" value="${param.txtSearchValue}" />
                <c:if test="${not empty searchValue}" >
                    <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
                    <div class="result row">
                        <c:if test="${not empty result}">
                            <c:set var="error" value="${requestScope.UPDATE_ERR}" />
                            <c:if test="${not empty error.passwordViolent}">
                                <font color="red">
                                ${error.passwordViolent}
                                </font>
                            </c:if>
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Username</th>
                                        <th>Password</th>
                                        <th>Full name</th>
                                        <th>Role</th>
                                        <th>Delete</th>
                                        <th>Update</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="dto" items="${result}" varStatus="counter">

                                    <form action="updateAction">
                                        <tr>
                                            <td style="text-align: center">
                                                ${counter.count}
                                            </td>
                                            <td>
                                                ${dto.username}
                                                <input type="hidden" name="txtUsername" value="${dto.username}" />
                                            </td>
                                            <td>
                                                <input type="text" name="txtPassword" value="${dto.password}" />
                                            </td>
                                            <td>
                                                ${dto.lastname}
                                            </td>
                                            <td style="text-align: center">
                                                <input type="checkbox" name="chkAdmin" value="ON" 
                                                       <c:if test="${dto.role eq true}">
                                                           checked="checked"
                                                       </c:if>
                                                       />
                                            </td>
                                            <td>
                                                <c:url var="urlRewriting" value="confirmDeleteAction">
                                                    <c:param name="pk" value="${dto.username}" />
                                                    <c:param name="lastSearchValue" value="${searchValue}" />
                                                </c:url>
                                                <c:if test="${isAdmin eq adminUserName}">
                                                    <c:if test="${dto.username ne adminUserName}">
                                                        <a href="${urlRewriting}" class="delete">Delete</a>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${isAdmin ne adminUserName}">
                                                    <c:if test="${dto.role eq false}">
                                                        <a href="${urlRewriting}" class="delete">Delete</a>
                                                    </c:if>
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${isAdmin eq adminUserName}">
                                                    <input type="submit" value="Update" class="btn"
                                                           <c:if test="${dto.username eq adminUserName}">
                                                               style="visibility: hidden"
                                                           </c:if>
                                                           />
                                                    <input type="hidden" name="lastSearchValue" 
                                                           value="${searchValue}" />
                                                </c:if>
                                                <c:if test="${isAdmin ne adminUserName}">
                                                    <input type="submit" value="Update" class="btn"
                                                           <c:if test="${isAdmin ne dto.username}">
                                                               <c:if test="${dto.role eq true}">
                                                                   style="visibility: hidden"
                                                               </c:if>
                                                           </c:if>
                                                           />
                                                    <input type="hidden" name="lastSearchValue" 
                                                           value="${searchValue}" />
                                                </c:if>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                            </table> 
                        </c:if>
                        <c:if test="${empty result}">
                            <h2>No record is matched!!!</h2>
                        </c:if>
                    </div>
                </c:if>
            </c:if>


            <!-- IF ADMIN FALSE -->     
            <c:if test="${empty isAdmin}">
                <div class="header row">
                    <h1>Your Profile</h1>
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
                                Role: Not Admin
                            </div>
                        </div>
                        <div>
                            <img src="./img/avatar.jpg" alt="Avatar">
                        </div>
                    </c:if>
                </div>
            </c:if>
        </div>
    </body>
</html>
