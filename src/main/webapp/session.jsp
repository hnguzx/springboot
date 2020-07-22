<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>session</title>
</head>
<body>
<%
    session.setAttribute("sessionName","sessionName");
    session.setAttribute("id",1L);
    response.sendRedirect("./session/test");
%>
</body>
</html>