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
    <title>Main Admin</title>
</head>
<body>
Hello ${user}
<hr/>
<form action="controller">
    <input type="hidden" name="command" value="find_client"/>
    <input type="submit" value="Clients"/>
    </br>
</form>
<form action="controller">
    <input type="hidden" name="command" value="find_tattoo"/>
    <input type="submit" value="Catalog"/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="delete_tattoo_page"/>
    <input type="submit" value="Delete attendance"/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="add_tattoo_page"/>
    <input type="submit" name="sub" value="Add attendance"/>
    <br/>
</form>
<form action="controller">
    <input type="hidden" name="command" value="find_order"/>
    <input type="submit" name="sub" value="Orders"/>
    <br/>
</form>
<form action="controller">
    <br/>
    <input type="hidden" name="command" value="logout"/>
    <input type="submit" value="logout"/>
</form>
<br/>
${delete_msg}
<br/>
${add_msg}
<br/>
${update_msg}
</body>
</html>
