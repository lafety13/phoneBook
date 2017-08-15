<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Contacts</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script src="../../resources/js/jquery-2.2.3.min.js" type="text/javascript"></script>
    <script src="../../resources/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../resources/css/css.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/t/bs/dt-1.10.11,se-1.1.2/datatables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/t/bs/dt-1.10.11,se-1.1.2/datatables.min.js"></script>
    <script type="text/javascript" src="../../resources/js/contactTable.js"></script>
</head>
<body>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/home"/>">Username: <c:out value="${username}"/></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-right" id="navbar">
            <ul class="nav navbar-nav">
                <li><a>Pages --> </a></li>
                <li><a href="<c:url value="/registration"/>"  >Sign Up</a></li>
                <li><a href="<c:url value="/signin"/>"  >Sign In</a></li>
                <li> <a href="/logout">Sign out</a></li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<hr style="margin-bottom: 150px">

<div class="container">
    <div class="row">
        <a class="btn btn-success btn-sm" id="newcontactbutton" style="margin: 20px 0 50px 20px; float: right">Add a new contact</a>
        <div class="col-sm-5" style="margin-top: 20px">
            <table class="table table-striped table-bordered" id="contacttable">
                <thead>
                <tr>
                    <th class="contactname col-sm-7">Ф.И.О.</th>
                    <th class="contactphone col-sm-5">Номер телефона</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="contact" items="${contacts}">
                    <tr class="clickable-row" id="${'row_'.concat(contact.id)}">
                        <td id="${'contactname_'.concat(contact.id)}">${contact.lastName} ${contact.firstName}</td>
                        <td id="${'contactphone_'.concat(contact.id)}">${contact.mobilePhone}</td>
                        <td>${contact.id}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-sm-5">
            <div class="col-sm-12 autowindow" style="" id="contactformwindow">
                <h1 id="formName">Add</h1>

                <c:url var="addAction" value="/contact/save"/>
                <form:form method="POST" id="contactform" action="${addAction}" modelAttribute="contactForm">

                    <div class="form-group">
                        <label for="id">Id</label>
                        <form:input path="id" class="form-control" id="id"  placeholder="id" readonly="true" />
                        <form:errors path="id" cssClass="error" />
                    </div>

                    <div class="form-group">
                        <label for="lastName">Last name</label>
                        <form:input path="lastName" class="form-control" id="lastName"  placeholder="lastName" />
                        <form:errors path="lastName" cssClass="error" />
                    </div>

                    <div class="form-group">
                        <label for="firstName">First name</label>
                        <form:input path="firstName" id="firstName" class="form-control" placeholder="firstName" />
                        <form:errors path="firstName" cssClass="error" />
                    </div>

                    <div class="form-group">
                        <label for="middleName">Middle name</label>
                        <form:input path="middleName" class="form-control" id="middleName"  placeholder="middleName" />
                        <form:errors path="middleName" cssClass="error" />
                    </div>

                    <div class="form-group">
                        <label for="mobilePhone">Mobile number</label>
                        <form:input path="mobilePhone" class="form-control" id="mobilePhone"  placeholder="mobilePhone" />
                        <form:errors path="mobilePhone" cssClass="error" />
                    </div>

                    <div class="form-group">
                        <label for="homePhone">Home number</label>
                        <form:input path="homePhone" class="form-control" id="homePhone"  placeholder="homePhone" />
                        <form:errors path="homePhone" cssClass="error" />
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <form:input path="address" class="form-control" id="address"  placeholder="address" />
                        <form:errors path="address" cssClass="error" />
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <form:input path="email" class="form-control" id="email"  placeholder="email" />
                        <form:errors path="email" cssClass="error" />
                    </div>

                    <div class="form-group">
                        <div class="col-sm-6">
                            <input name="submit" class="btn btn-primary col-sm-12" type="submit" id="submitbutton"
                                   value="Save">
                        </div>
                        <div class="col-sm-6">
                            <a class="btn btn-primary col-sm-12" style="display: none" id="deletecontactbutton">Delete</a>
                        </div>
                    </div>
                </form:form>

            </div>
        </div>
    </div>
</div>
</body>
</html>