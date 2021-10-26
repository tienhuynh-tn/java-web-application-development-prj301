<%-- 
    Document   : search
    Created on : Oct 5, 2021, 11:37:23 AM
    Author     : Huỳnh Lê Thủy Tiên <tien.huynhlt.tn@gmail.com>
--%>

<%--<%@page import="tienhlt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.FULL_NAME}
        </font> </br>
        <a href="DispatchServlet?btAction=logout">Log Out</a>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}" >
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" ></c:set>
            <c:if test="${not empty result}">
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
                        <form action="DispatchServlet">
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
                                    <c:url var="urlRewriting" value="DispatchServlet">
                                        <c:param name="btAction" value="Delete" />
                                        <c:param name="pk" value="${dto.username}" />
                                        <c:param name="lastSearchValue" value="${searchValue}" />
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${searchValue}" />
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



    <%-- <%--
        //1. Read cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie lastCookie = cookies[cookies.length - 1];
            %>
<!--                <font color="red">
                Welcome, <%-- lastCookie.getName() %>
            </font>-->
    <%
        }//end if cookies has existed
    %>
    <% 
        
        //1. Read cookies
        Cookie[] cookies = request.getCookies();
        String username = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String temp = cookie.getName();
                if (!temp.equals("JSESSIONID")) {
                    username = temp;
                }
            }
            %>
            <font color="red">
                Welcome, <%= username %>
            </font>
<%
        }
//            session.setAttribute("USER", username);
        String seusername = (String)session.getAttribute("USER");
        System.out.println(seusername + " SearchJSP");
    %>
    <a href="DispatchServlet?btAction=logout">Log Out</a>
    <h1>Search Page</h1>
    <form action="DispatchServlet">
        Search value <input type="text" name="txtSearchValue" 
                            value="<%= request.getParameter("txtSearchValue") %>" /> <br/>
        <input type="submit" value="Search" name="btAction" />
    </form> <br/>
    <%
        String searchValue = request.getParameter("txtSearchValue");
        if (searchValue != null) {
            List<RegistrationDTO> result
                    = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
            if (result != null) {//has result
    %>
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
            <%
                int count = 0;
                for (RegistrationDTO dTO : result) {
                    String urlRewriting = "DispatchServlet"
                            + "?btAction=delete"
                            + "&pk=" + dTO.getUsername()
                            + "&lastSearchValue=" + searchValue;
            %>
        <form action="DispatchServlet">
            <tr>
                <td>
                    <%= ++count%>
                </td>
                <td>
                    <%= dTO.getUsername()%>
                    <input type="hidden" name="txtUsername" 
                           value="<%= dTO.getUsername()%>" />
                </td>
                <td>
                    <input type="text" name="txtPassword" 
                           value="<%= dTO.getPassword()%>" />
                </td>
                <td>
                    <%= dTO.getLastname() %>
                </td>
                <td>
                    <input type="checkbox" name="chkAdmin" value="ON" 
                           <%
                               if (dTO.isRole()) {
                           %>
                           checked="checked"
                           <%
                                                   }//end if role is an admin
%>
                           />
                </td>
                <td>
                    <a href="<%= urlRewriting%>">Delete</a>
                </td>
                <td>
                    <input type="submit" value="Update" name="btAction" />
                    <input type="hidden" name="lastSearchValue" 
                           value="<%= searchValue%>" />
                </td>
            </tr>
        </form>
        <%
            }//end for traverse result list
        %>
    </tbody>
</table>

    <%
    } else {//not match
    %>
    <h2>
        No record is matched!!!
    </h2>
    <%
            }
        }//end if search Value had inpputed
%>--%>
</body>
</html>
