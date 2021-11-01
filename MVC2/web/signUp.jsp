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
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/grid.css">
        <link rel="stylesheet" href="./css/signUpStyle.css">
    </head>
    <body>
        <div class="container">
            <div class="grid">
                <div class="row">
                    <h1>Create New Account</h1>
                </div>

                <div class="main row">
                    <form action="signUpAction" method="POST">
                        <c:set var="errors" value="${requestScope.SIGNUPERRS}" />

                        <div class="item">
                            <div class="title">
                                Username * 
                                <span class="guide">
                                    {e.g. 6 - 30 chars}
                                </span>  
                            </div>
                            <input type="text" name="txtUsername" value="${param.txtUsername}" class="text-box" /> 
                            
                            <c:if test="${not empty errors.usernameLengthViolent}">
                                <font color="red">
                                ${errors.usernameLengthViolent}
                                </font>
                            </c:if>

                            <c:if test="${not empty errors.usernameIsExisted}">
                                <font color="red">
                                ${errors.usernameIsExisted}
                                </font>
                            </c:if>
                        </div>

                        <div class="item">
                            <div class="title">
                                Password * 
                                <span class="guide">
                                    {e.g. 6 - 20 chars}
                                </span> 
                            </div>
                            <input type="password" name="txtPassword" value="" class="text-box" /> 
                            
                            <c:if test="${not empty errors.passwordViolent}">
                                <font color="red">
                                ${errors.passwordViolent}
                                </font>
                            </c:if>
                        </div>

                        <div class="item">
                            <div class="title">
                                Confirm *
                            </div>
                            <input type="password" name="txtConfirm" value="" class="text-box" /> </br>
                            <c:if test="${not empty errors.confirmNotMatch}">
                                <font color="red">
                                ${errors.confirmNotMatch}
                                </font>
                            </c:if>
                        </div>

                        <div class="item">
                            <div class="title">
                                First name * 
                                <span class="guide">
                                    {e.g. 2 - 50 chars}
                                </span> 
                            </div>
                            <input type="text" name="txtFirstname" value="${param.txtFirstname}" class="text-box" /> 
                            
                            <c:if test="${not empty errors.firstNameLengthViolent}">
                                <font color="red">
                                ${errors.firstNameLengthViolent}
                                </font>
                            </c:if>
                        </div>

                        <div class="item">
                            <div class="title">
                                Middle name 
                                <span class="guide">
                                    {e.g. 0 - 50 chars} 
                                </span>
                            </div>
                            <input type="text" name="txtMiddlename" value="${param.txtMiddlename}" class="text-box" /> 
                            
                            <c:if test="${not empty errors.middleNameLengthViolent}">
                                <font color="red">
                                ${errors.middleNameLengthViolent}
                                </font>
                            </c:if>
                        </div>

                        <div class="item">
                            <div class="title">
                                Last name * 
                                <span class="guide">
                                    {e.g. 2 - 50 chars} 
                                </span>
                            </div>
                            <input type="text" name="txtLastname" value="${param.txtLastname}" class="text-box" /> 
                            
                            <c:if test="${not empty errors.lastNameLengthViolent}">
                                <font color="red">
                                ${errors.lastNameLengthViolent}
                                </font>
                            </c:if>
                        </div>

                        <div class="footer">
                            <div class="button">
                                <input type="submit" value="Sign Up" class="btn" />
                                <input type="reset" value="Reset" class="btn" />
                            </div>
                            <div class="login">
                                <span class="text">Have already an account?</span>
                                <a href="loginPage">Login Here!</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
