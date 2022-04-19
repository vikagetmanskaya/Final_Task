<%--
  Created by IntelliJ IDEA.
  User: 37529
  Date: 18.04.2022
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Catalog</title>
</head>
<body>

<table>
    <tr>
        <th>Name</th>
        <th>Master's first name</th>
        <th>Master's last name</th>
        <th>Size in cm</th>
        <th>Date added</th>
        <th>Rating</th>
    </tr>

    <c:forEach var="tattooList" items="${tattoos}">
        <tr>
            <td>${tattooList.name}</td>
            <td>${tattooList.firstNameMaster}</td>
            <td>${tattooList.lastNameMaster}</td>
            <td>${tattooList.size}</td>
            <td>${tattooList.dateAdded}</td>
            <td>${tattooList.rating}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
