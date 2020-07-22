<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增用户</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#submit").click(function () {
                var userName = $("#userName").val();
                var note = $("#note").val();
                if ($.trim(userName) == '') {
                    alert("用户名不能为空");
                    return;
                }
                if ($.trim(note) == '') {
                    alert("备注不能为空");
                    return;
                }
                var params = {
                    userName: userName,
                    note: note
                }

                $.post({
                    // url: "./insert",
                    url: "./redirect1",
                    contentType: "application/json",
                    data: JSON.stringify(params),
                    success: function (result) {
                        if (result == null || result.id == null) {
                            alert("添加用户失败");
                            return;
                        }
                        alert("添加用户成功！");
                    }
                });
            });
        })
    </script>
</head>
<body>
<div style="margin:20px 0"></div>
<form id="insertForm">
    <table>
        <tr>
            <td>用户名称</td>
            <td>
                <input id="userName" name="userName">
            </td>
        </tr>
        <tr>
            <td>备注</td>
            <td>
                <input id="note" name="note">
            </td>
        </tr>
        <tr>
            <td></td>
            <td align="right">
                <input id="submit" type="button" value="提交">
            </td>
        </tr>

    </table>
</form>
</body>
</html>