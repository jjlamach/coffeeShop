<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="a" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sweets & Coffee</title>
    <script src= "${pageContext.request.contextPath}/resources/jquery/jquery.js"></script>
    <script src= "${pageContext.request.contextPath}/resources/js/coffee.js"></script>
    <link rel="stylesheet" href= ${pageContext.request.contextPath}/resources/css/coffee.css>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
          crossorigin="anonymous">
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
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Sign Out</a>
            </li>
        </div>
    </div>
</nav>

<br/>
<br/>

<%--List of coffee products available--%>
<%--Classes with col-lg-5 or col-md etc look different on devices
they resize when screen shrinks--%>


<div class="container" align="center">
    <div class="col-lg-4">
        <div class="card">
            <h6 class="card-header" id="coldBrew">
                Coffee
            </h6>
            <img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/Rcoffee.png"
                 style="width: 150px; height: 176px">
            <div class="card-body">
                <p class="card-text">

                </p>
            </div>
            <footer class="card-footer">
                <%--Added the CSS class of button to the form element since it is wrapping the submit input --%>

                <form:form action="${pageContext.request.contextPath}/addCoffeeToCart" method="POST"
                           cssClass="btn btn-sm btn-primary lef-button">
                    <input style="background: transparent; border: none; color:white" type="submit" value="Add to cart"/>
                </form:form>
                <form:form action="${pageContext.request.contextPath}/removeFromCart" method="POST"
                           cssClass="btn btn-sm btn-danger right-button">
                    <input style="background: transparent; border: none; color: white" type="submit" value="Remove from cart"/>
                </form:form>

                <br/>
                <br/>
                <strong>Price:</strong> $1.50
            </footer>
        </div>
        <br/>

        <div class="card">
            <h6 class="card-header">
                Cappuccino
            </h6>
            <img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/capuccino.png"
                 style="width: 150px; height: 150px;">
            <div class="card-body">
                A cappuccino is an espresso-based coffee drink that originated in Italy,
                and is traditionally prepared with double espresso and steamed milk foam.
                Variations of the drink involve the use of cream instead of milk,
                and flavoring with cinnamon or chocolate powder.
            </div>
            <footer class="card-footer">
                <button class="btn btn-sm btn-primary left-button">
                    Add to cart
                </button>

                <button class="btn btn-sm btn-danger right-button">
                    Remove from cart
                </button>
                <br/> <br/>
                <strong>Price:</strong> $2.00
            </footer>
        </div>
        <br/>

        <div class="card">
            <h6 class="card-header">
                Cold Brew
            </h6>
            <img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/brew.png"
                 style="width: 150px; height: 250px;">

            <div class="card-body">
                <p class="card-text">
                    Cold brew uses time, rather than heat to extract the coffee's oils, sugars, and caffeine.
                    Iced coffee is created like any regular hot coffee brew method.
                    It's simply hot coffee that has been cooled down so that it can be poured over ice.
                </p>
            </div>

            <footer class="card-footer">
                <button type="submit" name="coldBrew" class="btn btn-sm btn-primary left-button">Add to cart</button>
                <button type="submit" class="btn btn-sm btn-danger right-button">Remove from cart</button>
                <br/><br/>
                <strong>Price:</strong> $3.50
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
