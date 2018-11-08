<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"  session="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sweets & Coffee</title>
    <script src= "${pageContext.request.contextPath}/resources/jquery/jquery.js"></script>
    <script src= "${pageContext.request.contextPath}/resources/js/coffee.js"></script>
    <link rel="stylesheet" href= ${pageContext.request.contextPath}/resources/css/coffee.css>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
          crossorigin="anonymous">
</head>
<body style="background-color: ghostwhite">
<br><br>
<%--Imagen--%>
<div class="container" align="center">
    <%--<img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/randomFood.png"--%>
    <%--style="width: 120px; height: 120px;">--%>
    <img src="<c:url value="/resources/images/randomFood.png"/>" style="width: 120px; height: 120px;">
</div>

<%-- When this form is submmited the setters of the Spring Bean will be called and will be filled with the
input values. --%>
<form:form action="/authenticateUser" method="POST" cssClass="container col-lg-4" modelAttribute="verifyIncomingCustomer">
    <%--
        Spring appends (?) a parameter named "error" if the credentials entered are wrong.
        We can add our own error-message when this parameter exists in our URL.
    <%--&ndash;%&gt;--%>
    <%--<c:if test="${param.error != null}">--%>
    <%--<i style="color:red">--%>
    <%--Sorry, bad credentials! <br/>--%>
    <%--Wrong username or password--%>
    <%--</i>--%>
    <%--</c:if>--%>

    <%--<c:if test="${param.logout != null}">--%>
    <%--<i style="color: darkgreen">--%>
    <%--You've been logged out!--%>
    <%--</i>--%>
    <%--</c:if>--%>



    <div class="form-group">
        <label for="userName">Username</label>
            <%-- Spring expects the name to be "username" --%>
            <%--<input type="text" name="username" id="userName" class="form-control"/>--%>
        <form:input path="username" cssClass="form-control" required="required"/>
    </div>
    <div class="form-group">
        <label for="passWord">Password</label>
            <%-- Spring expects the name to be "password" --%>
            <%--<input type="password" name="password" id="passWord" class="form-control"/>--%>
        <form:password path="password" cssClass="form-control" required="required"/>
    </div>
    <input type="submit" value="Log in" class="btn btn-sm btn-outline-primary"/>
    <input type="button" value="Create account" class="btn btn-sm btn-outline-danger"
           onclick="window.location.href='/registration'"/>
</form:form>




<%--Bootstrap--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
      integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
      crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
        integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
</body>
</html>
