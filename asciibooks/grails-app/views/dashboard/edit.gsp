<%--
  Created by IntelliJ IDEA.
  User: boyanmihovski
  Date: 10/12/2019
  Time: 22:02
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin"/>
    <title>AsciiBooks - ${book.title}</title>
</head>

<body>
    <div class="container">
        <div class="row">
            <label>
                <textarea>${book.content}</textarea>
            </label>
        </div>
    </div>

</body>
</html>