
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Registration</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/css.css"/>" rel="stylesheet">

    <!-- Custom CSS -->
    <style>
        body {
            padding-top: 70px;
            /* Required padding for .navbar-fixed-top. Remove if using .navbar-static-top. Change if height of navigation changes. */
        }
    </style>
</head>
<body>

<!--     Navigable     -->
<%@include file="menu.jsp"%>

<!-- Page Content -->
<div id="content" class="container">

    <div id="container" class="container">
        <h1>Sign Up</h1>
        <hr>
        <form:form method="POST" modelAttribute="userForm">

            <div class="form-group">
                <label for="login">Login</label>
                <form:input path="login" class="form-control" id="login"  placeholder="Login" />
                <form:errors path="login" cssClass="error" />
            </div>

            <div class="form-group">
                <label for="name">Name</label>
                <form:input path="name" id="name" class="form-control" placeholder="Name" />
                <form:errors path="name" cssClass="error" />
            </div>

            <div class="form-group">
                <label for="pass">Password</label>
                <form:input type="password" path="password" class="form-control" id="pass"  placeholder="Password" />
                <form:errors path="password" cssClass="error" />
            </div>

            <div class="form-group">
                <label for="confirmPassword">Password</label>
                <form:input type="password" path="confirmPassword" class="form-control" id="confirmPassword"  placeholder="Confirm password" />
                <form:errors path="confirmPassword" cssClass="error" />
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>

    </div>

</div>
<!-- /.container -->

<!-- jQuery Version 1.11.1 -->
<script src="<c:url value="/resources/js/jquery.js"/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

</body>
</html>