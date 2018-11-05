<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src= "${pageContext.request.contextPath}/resources/jquery/jquery.js"></script>
    <script src= "${pageContext.request.contextPath}/resources/js/coffee.js"></script>
    <link rel="stylesheet" href= ${pageContext.request.contextPath}/resources/css/coffee.css>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
          crossorigin="anonymous">
    <title>Sweets & Coffee</title>
</head>
<body>
<br/>


<h4 class="header text-center">Registration</h4>
<div class="container col-lg-3">
    <form:form action="/registerCustomer" method="POST" modelAttribute="customer">

        <div class="form-group">
            <label for="firstName">First name</label>
            <form:input path="firstName" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label fo="lastName">Last name</label>
            <form:input path="lastName" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label for="address">Address</label>
            <form:input path="address" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label for="username">Username</label>
            <form:input path="username" cssClass="form-control"/>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <form:password path="password" showPassword="false" cssClass="form-control"/>
        </div>
        <%-- To submit the form. --%>
        <input type="submit" class="btn btn-sm btn-outline-success" value="Register"/>
        <input type="button" class="btn btn-sm btn-outline-danger" value="Back to log in"
        onclick="window.location.href='/login'"/>
    </form:form>
</div>

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
