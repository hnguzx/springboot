<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>表单定义http动作</title>
</head>
<body>
<form action="./name" id="form" method="post">
    <table>
        <tr>
            <td>用户编号</td>
            <td><input type="text" id="id" name="id"></td>
        </tr>
        <tr>
            <td>用户名称</td>
            <td><input type="text" id="userName" name="userName"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" id="submit" name="submit"></td>
        </tr>
    </table>
    <input type="hidden" name="_method" id="_method" value="PATCH">
</form>
</body>
</html>