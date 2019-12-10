<%--
  Created by IntelliJ IDEA.
  User: boyanmihovski
  Date: 10/12/2019
  Time: 22:06
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="admin">
    <title>Asciibooks - Dashboard</title>
</head>

<body>
    <div class="container">
        <div class="row">
            <h3>My Books</h3>
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Title</th>
                    <th>Published</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${books}" var="book">
                     <tr>
                         <td><a href="${createLink(action: 'edit', params: [id: book.id])}">${book.id}</a></td>
                         <td>${book.title}</td>
                         <td>${book.published ? 'Yes': 'No' }</td>
                         <td>${book.formattedPrice}</td>
                     </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>