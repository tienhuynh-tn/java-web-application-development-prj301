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
        <form action="signUpAction" method="POST">
            <c:set var="errors" value="${requestScope.SIGNUPERRS}" />
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> {e.g. 6 - 30 chars} </br>
            <c:if test="${not empty errors.usernameLengthViolent}">
                <font color="red">
                    ${errors.usernameLengthViolent}
                </font> </br>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> {e.g. 6 - 20 chars} </br>
            <c:if test="${not empty errors.passwordViolent}">
                <font color="red">
                    ${errors.passwordViolent}
                </font> </br>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /> </br>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                    ${errors.confirmNotMatch}
                </font> </br>
            </c:if>
            First name* <input type="text" name="txtFirstname" value="${param.txtFirstname}" /> {e.g. 2 - 50 chars} </br>
            <c:if test="${not empty errors.firstNameLengthViolent}">
                <font color="red">
                    ${errors.firstNameLengthViolent}
                </font> </br>
            </c:if>
            Middle name <input type="text" name="txtMiddlename" value="${param.txtMiddlename}" /> {e.g. 0 - 50 chars} <br/>
            <c:if test="${not empty errors.middleNameLengthViolent}">
                <font color="red">
                    ${errors.middleNameLengthViolent}
                </font> </br>
            </c:if>
            Last name* <input type="text" name="txtLastname" value="${param.txtLastname}" /> {e.g. 2 - 50 chars} <br/>
            <c:if test="${not empty errors.lastNameLengthViolent}">
                <font color="red">
                    ${errors.lastNameLengthViolent}
                </font> </br>
            </c:if>
            <input type="submit" value="Sign Up" />
            <input type="reset" value="Reset" />
        </form> </br>
        <c:if test="${not empty errors.usernameIsExisted}">
            <font color="red">
                ${errors.usernameIsExisted}
            </font> </br>
        </c:if>
    </body>
</html>
