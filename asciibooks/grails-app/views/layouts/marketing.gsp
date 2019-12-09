<%--
  Created by IntelliJ IDEA.
  User: bmihovski
  Date: 12/9/19
  Time: 11:13 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:layoutTitle default="Asciibooks.com"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <g:layoutHead/>
</head>

<body>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="#">AsciiBooks</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsDefault" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsDefault">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${createLink(controller: 'signUp', action: 'join')}">Sign up</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${createLink(controller: 'login', action: 'auth')}">Login</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false">Books</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown01">
                        <a class="dropdown-item" href="#">Top 10</a>
                        <a class="dropdown-item" href="#">List</a>
                        <a class="dropdown-item" href="${createLink(controller: 'signUp', action: 'join')}">Publish your own!</a>
                    </div>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="text" placeholder="Title or Author" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
    <g:layoutBody/>
    <asset:javascript src="application.js"/>
    <asset:deferredScripts/>
</body>
</html>