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
    <a class="navbar-brand" href="/">
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
                <a class="nav-link" href="#">Sign Out</a>
            </li>
        </div>
    </div>
</nav>

<br/>
<br/>

<div class="container" align="center">
    <%--How many columns to occupy: 4 --%>
    <div class="col-lg-4">
        <div class="card">
            <h6 class="card-header">
                Strawberry Cheesecake
            </h6>
            <img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/Strawberry-Cheese-Cake.png"
                 style="width: 150px; height: 150px;">

            <div class="card-body">
                <p class="card-text">
                    Try our cheesecake!
                </p>
            </div>
            <footer class="card-footer">
                <form:form method="POST" action="${pageContext.request.contextPath}/purchase" cssClass="btn btn-sm btn-primary">
                    <input type="hidden" name="orderType" value="Strawberry Cheesecake"/>
                    <input type="hidden" name="price" value="5.50"/>
                    <textarea hidden="true" name="orderDescription">
                        Cheesecake with lot of strawberries
                    </textarea>
                    <input type="submit" value="Add to cart" style="background: transparent; color:white; border: none;"/>
                </form:form>
            </footer>
        </div>
        <br/>
        <div class="card">
            <h6 class="card-header">
               Cake with lot of oreo
            </h6>
            <img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/oreo.png"
                 style="width: 150px; height: 150px;">
            <div class="card-body">
                <p class="card-text">
                    Give our Oreo cheesecake a try!
                </p>
            </div>
            <footer class="card-footer">
                <form:form cssClass="btn btn-sm btn-primary" method="POST" action="${pageContext.request.contextPath}/purchase">
                    <input type="hidden" name="orderType" value="Oreo Cheesecake"/>
                    <input type="hidden" name="price" value="5.50"/>
                    <textarea hidden="true" name="orderDescription">
                        Oreo Cheesecake
                    </textarea>
                    <input type="submit" style="border: none; background: transparent; color:white;"
                    value="Add to cart"/>
                </form:form>
            </footer>
        </div>

    </div>
</div>

<%--Shopping cart as a navbar--%>
<a class="navbar float-lg-right" href="${pageContext.request.contextPath}/shoppingCart">
    <div class="text-center">
        <i class="fa fa-shopping-cart fa-4x"></i>
    </div>
</a>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
</body>
</html>