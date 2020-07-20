<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>用户信息</title>
</head>
<body>
<table>
    <tr>
        <td>编号</td>
        <td>${user.id}</td>
    </tr>
    <tr>
        <td>用户名</td>
        <td>${user.userName}</td>
    </tr>
    <tr>
        <td>备注</td>
        <td>${user.note}</td>
    </tr>
</table>
</body>
</html>