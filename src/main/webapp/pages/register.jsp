<%--
  Created by IntelliJ IDEA.
  User: 37529
  Date: 11.04.2022
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<input type="hidden" name="command" value="add_user"/>
Email: <input type="text" name="email" value=""/>
<br/>
Password: <input type="password" name="pass" value=""/>
<br/>
First name: <input type="text" name="first_name" value=""/>
<br/>
Last name: <input type="text" name="last_name" value=""/>
<br/>
Date of birth: <input type="date" name="date_of_birth" value=""/>
<br/>
<input type="submit" name="sub" value="Push"/>
<br/>
${login_msg}

</body>
</html>
