<%--
  Created by IntelliJ IDEA.
  User: 37529
  Date: 29.03.2022
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
Hello(forward) ${user}
<hr/>
Hi(redirect/forward) ${user_name}
<hr/>
${filter_attr}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="logout"/>
<input type="submit" value="logout"/>
</form>
</body>
</html>
