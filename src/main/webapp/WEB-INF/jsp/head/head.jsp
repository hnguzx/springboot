<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>获取请求头参数</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $.post({
            url: "./user",
            headers: {id: '1'},
            success: function (user) {
                if (user == null || user.id == null) {
                    alert("获取信息失败");
                    return
                }
                alert("user_info:" + user.id + "user_name:" + user.userName)
            }
        });
    </script>
</head>
</html>