<%-- 
    Document   : signUp
    Created on : Oct 21, 2021, 11:49:40 AM
    Author     : Huynh Le Thuy Tien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.SIGNUPERRS}" />
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> {e.g. 6 - 30 chars} </br>
            <c:if test="${not empty errors.usernameLengthViolent}">
                <font color="red">
                    ${errors.usernameLengthViolent}
                </font> </br>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> {e.g. 6 - 20 chars} </br>
            <c:if test="${not empty errors.passwordLengthViolent}">
                <font color="red">
                    ${errors.passwordLengthViolent}
                </font> </br>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /> </br>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                    ${errors.confirmNotMatch}
                </font> </br>
            </c:if>
                Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" /> {e.g. 2 - 50 chars} </br>
            <c:if test="${not empty errors.fullNameLengthViolent}">
                <font color="red">
                    ${errors.fullNameLengthViolent}
                </font> </br>
            </c:if>
            <input type="submit" value="Sign Up" name="btAction" />
            <input type="reset" value="Reset" />
        </form> </br>
        <c:if test="${not empty errors.usernameIsExisted}">
            <font color="red">
                ${errors.usernameIsExisted}
            </font> </br>
        </c:if>
    </body>
</html>
