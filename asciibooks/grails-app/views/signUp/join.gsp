<%--
  Created by IntelliJ IDEA.
  User: boyanmihovski
  Date: 10/12/2019
  Time: 22:45
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="marketing">
    <title>AsciiBooks - Sign Up</title>
</head>

<body>
<div class="container">
    <form action="${createLink(controller: 'signUp', action: 'save')}" method="POST" autocomplete="on">
        <h2 class="form-signin-heading">Become an author today!</h2>
        <label for="email" class="sr-only">Email address</label>
        <input type="email" id="email" name="email" class="form-control" placeholder="Email address" required autofocus>
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        <label for="author.name" class="sr-only">Name</label>
        <input type="text" id="author.name" name="author.name" class="form-control" placeholder="Name" required>
        <label for="author.penName" class="sr-only">Pen Name</label>
        <input type="text" id="author.penName" name="author.penName" class="form-control" placeholder="Pen Name">


        <label for="author.address.street" class="sr-only">Street</label>
        <input type="text" id="author.address.street" name="author.address.street" class="form-control" placeholder="Street">
        <label for="author.address.city" class="sr-only">City</label>
        <input type="text" id="author.address.city" name="author.address.city" class="form-control" placeholder="City">
        <label for="author.address.state" class="sr-only">Street</label>
        <input type="text" id="author.address.state" name="author.address.state" class="form-control" placeholder="State">
        <label for="author.address.zipCode" class="sr-only">Street</label>
        <input type="number" id="author.address.zipCode" name="author.address.zipCode" class="form-control" placeholder="ZipCode">

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up!</button>
    </form>
</div>
</body>
</html>