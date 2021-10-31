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
        <meta http-equiv="Expires" content="0">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.FULL_NAME}
        </font> </br>
        <a href="logoutAction">Log Out</a>

        <c:set var="isAdmin" value="${sessionScope.ADMIN}" />

        <!-- IF ADMIN TRUE -->
        <c:if test="${not empty isAdmin}">
            <h1>Search Page</h1>
            <form action="searchAction">
                Search value <input type="text" name="txtSearchValue" 
                                    value="${param.txtSearchValue}" /> <br/>
                <input type="submit" value="Search" name="btAction" />
            </form> <br/>

            <c:set var="searchValue" value="${param.txtSearchValue}" />
            <c:if test="${not empty searchValue}" >
                <c:set var="result" value="${requestScope.SEARCH_RESULT}" ></c:set>
                <c:if test="${not empty result}">
                    <c:set var="error" value="${requestScope.UPDATE_ERR}" />
                    <c:if test="${not empty error.passwordLengthViolent}">
                        <font color="red">
                        ${error.passwordLengthViolent}
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
                                    <td>
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
                                    <td>
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
                                        <c:if test="${isAdmin eq 'admin123'}">
                                            <c:if test="${dto.username ne 'admin123'}">
                                                <a href="${urlRewriting}">Delete</a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${isAdmin ne 'admin123'}">
                                            <c:if test="${dto.role eq false}">
                                                <a href="${urlRewriting}">Delete</a>
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${isAdmin eq 'admin123'}">
                                            <input type="submit" value="Update"
                                                   <c:if test="${dto.username eq 'admin123'}">
                                                       disabled
                                                   </c:if>
                                                   />
                                            <input type="hidden" name="lastSearchValue" 
                                                   value="${searchValue}" />
                                        </c:if>
                                        <c:if test="${isAdmin ne 'admin123'}">
                                            <input type="submit" value="Update"
                                                   <c:if test="${isAdmin ne dto.username}">
                                                       <c:if test="${dto.role eq true}">
                                                           disabled
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
        </c:if>
    </c:if>
                 
        <!-- IF ADMIN FALSE -->     
        <c:if test="${empty isAdmin}">
            <h1>Your Profile</h1>
            <c:set var="dto" value="${sessionScope.SHOW_PROFILE}" />
            <c:if test="${not empty dto}">
                Username: ${dto.username} <br/>
                Password: ${dto.password} <br/>
                Full Name: ${dto.lastname} <br/>
                Role: Not Admin
            </c:if>
        </c:if>
    </body>
</html>
