
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign In</title>
    <script src="../../resources/js/jquery-2.2.3.min.js" type="text/javascript"></script>
    <script src="../../resources/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
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
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-right" id="navbar">

        </div>

        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<hr style="margin-bottom: 150px">

<div class="container">
    <div class="row">
        <div class="col-sm-4 autowindow">
            <form class="form-horizontal" id="signin" action="/j_spring_security_check" method="post">
                <h3>Выполните вход</h3>
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="j_username" class="control-label">Логин</label>
                    </div>
                    <div class="col-sm-8">
                        <input name="j_username" type="text" class="form-control" id="j_username"
                               placeholder="email">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="j_password" class="control-label">Пароль</label>
                    </div>
                    <div class="col-sm-8">
                        <input name="j_password" type="password" class="form-control" id="j_password"
                               placeholder="password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-6">
                        <a class="btn btn-primary col-sm-12" href="/registration">Регистрация</a>
                    </div>
                    <div class="col-sm-6 rightbutton">
                        <input class="btn btn-primary col-sm-12" type="submit" value="Войти">
                    </div>
                </div>
            </form>
            <c:if test="${param.error!=null}">
                <div class="alert alert-warning">
                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </div>
            </c:if>
            <c:if test="${param.logout!=null}">
                <div class="alert alert-warning">
                    Вы разлогиниись.
                </div>
            </c:if>
            <c:if test="${not empty message}">
                <div class="alert alert-success">
                        ${message}
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
