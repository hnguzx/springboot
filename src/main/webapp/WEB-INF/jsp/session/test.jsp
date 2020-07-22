<%@ page import="com.guzx.chapter2.pojo.User_MyBatis" %>
<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>session</title>
</head>
<body>
<%
    User_MyBatis user_myBatis = (User_MyBatis) session.getAttribute("user");
    Long id = (Long) session.getAttribute("id_new");

    out.print("<br>user_name = " + user_myBatis.getUserName());
    out.print("<br>id_name = " + id);
%>
</body>
</html>