<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>格式化</title>
</head>
<body>
<form action="./commit" method="post">
    <table>
        <tr>
            <td>日期（yyyy-dd-mm）</td>
            <td>
                <input type="text" name="date" value="2020-02-02">
            </td>
        </tr>
        <tr>
            <td>金额（#,###.##）</td>
            <td>
                <input type="text" name="number" value="1,234.56">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right">
                <input type="submit" value="提交">
            </td>
        </tr>
    </table>
</form>
</body>
</html>