<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sweets & Coffee</title>
    <script src="${pageContext.request.contextPath}/resources/jquery/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/coffee.js"></script>
    <link rel="stylesheet" href=${pageContext.request.contextPath}/resources/css/coffee.css>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link rel="icon" typeof="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
        <img src=${pageContext.request.contextPath}/resources/images/greenLeaf.png width="30" height="30" alt="">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#coffeeNavBarLinks" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="coffeeNavBarLinks">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/products">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/shoppingCart">Shopping Cart</a>
            </li>
        </ul>
        <%--Lol... Use another Navbar.--%>
        <div class="navbar-nav">
            <li class="nav-item">
                <%--<a class="nav-link" href="${pageContext.request.contextPath}/logout">Sign Out</a>--%>
                <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                    <input class="btn btn-sm btn-outline-info" type="submit" value="Sign Out"/>
                </form:form>
            </li>
        </div>
    </div>
</nav>
<br/>
<br/>

<div class="container" align="center">
    <img class="img-thumbnail" src="${pageContext.request.contextPath}/resources/images/shoppingCart.png"
         style="width: 350px; height: 350px; border-width: thick; border-color: cadetblue ">
    <br/><br/>
    <h2>Hi, ${currentCustomer.username}</h2>
    <span class="float-right" style="padding-right: 60px;">
        <strong>Total:</strong> $${total}
    </span>
    <div class="table-responsive">
        <table class="table table-hover table-striped table-bordered">
            <thead>
            <tr>
                <th scope="col">Product</th>
                <th scope="col">Description</th>
                <th scope="col">Price</th>
                <th scope="col"> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderList}" var="order">
                <c:url var="deleteLink" value="${pageContext.request.contextPath}/removeFromCart">
                    <c:param name="order_number" value="${order.id}"/>
                </c:url>
                <tr>
                    <td>${order.name}</td>
                    <td>${order.description}</td>
                    <td>$${order.price}</td>
                    <td>
                        <a href="${deleteLink}"><i class="fas fa-trash-alt fa-2x"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>



<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
</body>
</html>
