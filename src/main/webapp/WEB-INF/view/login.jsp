<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/randomFood.png"
         style="width: 120px; height: 120px;">
</div>
<%--
    Login form: llama al Controller; invoka el URL: /home y hace el metodo post.
--%>
<%-- ModelAttribute will bind the input data to the model: "incomingCustomer" that is the controller in the method
   login(). --%>
<form:form action="login" modelAttribute="incomingCustomer" method="POST" cssClass="container col-lg-4">
    <%--So Spring knows about which customer through their IDs--%>
    <form:hidden path="id"/>
    <div class="form-group">
        <label for="fName">First name</label>
        <form:input path="firstName" cssClass="form-control" id="fName"/>
    </div>

    <div class="form-group">
        <label for="lName">Last name</label>
        <form:input path="lastName" cssClass="form-control" id="lName"/>
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <form:input path="password" cssClass="form-control" id="password"/>
    </div>

    <input class="btn btn-sm btn-outline-primary  left-button" type="submit" value="Login"/>
    <input onclick="window.location.href='/createAccount'"
           class="btn btn-sm btn-warning right-button" type="button" value="Create account"/>
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
