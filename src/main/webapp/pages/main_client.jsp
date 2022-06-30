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
    <title>Main Client</title>
</head>
<body>
Hello ${user}

<hr/>
<form action="controller">
    <input type="hidden" name="command" value="find_service"/>
    <input type="submit" value="Catalog"/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="make_order_page"/>
    <input type="submit" value="Make order"/>
</form>
<form action="controller">
    <br/>
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="logout"/>
</form>
<br/>
${add_msg}
</body>
</html>
