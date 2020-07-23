<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>restful</title>
    <script type="text/javascript" src="../jquery-easyui-1.9.7/jquery.min.js"></script>
    <script type="text/javascript">
        function post() {
            var params = {
                'userName': 'restfulName',
                'sexCode': 1,
                'note': 'this is note'
            }

            $.post({
                url: './user',
                contentType: 'application/json',
                data: JSON.stringify(params),
                success: function (result) {
                    if (result != null && result.id != null) {
                        alert("插入成功！")
                        return;
                    }
                    alert("插入失败！")
                }
            });
        }

        function get() {
            $.get("./user/20", function (user, status) {
                if (user != null) {
                    alert("用户信息为：" + JSON.stringify(user));
                    return;
                }
                alert("获取用户信息失败");
            });
        }

        function get2() {
            $.get("./user2/20", function (user, status) {
                if (user != null) {
                    alert("用户信息为：" + JSON.stringify(user));
                    return;
                }
                alert("获取用户信息失败");
            });
        }

        function getList() {
            $.get("./user/restfulName/this is note/0/2", function (user, status) {
                if (user != null) {
                    alert("用户信息为：" + JSON.stringify(user));
                    return;
                }
                alert("获取用户信息失败");
            });
        }

        function updateUser() {
            var params = {
                'userName': 'updateUserName',
                'sexCode': 1,
                'note': 'this is new Note'
            }

            $.post({
                url: './user/1',
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(params),
                success: function (result) {
                    if (result != null && result.id != null) {
                        alert("更新成功：" + JSON.stringify(result));
                        return;
                    }
                    alert("更新失败！")
                }
            });
        }

        function updateUserName() {
            $.ajax({
                url: './user/1/谷志雄',
                type: 'PATCH',
                success: function (result) {
                    if (result == null) {
                        alert("更新失败")
                        return;
                    }
                    alert("更新成功！" + JSON.stringify(result));
                }
            });
        }

        function deleteUser() {
            $.ajax({
                url: './user/1',
                type: 'DELETE',
                success: function (result) {
                    if (result.success == false) {
                        alert("删除失败")
                        return;
                    }
                    alert("删除成功！" + JSON.stringify(result));
                }
            });
        }

        function postStatus() {
            var params = {
                'userName': 'postMan',
                'sexCode': 1,
                'note': 'Note'
            }

            var url = './user2/entity';
            // var url = './user2/annotation';
            $.post({
                url: url,
                contentType: 'application/json',
                data: JSON.stringify(params),
                success: function (result, status, jqXHR) {
                    var success = jqXHR.getResponseHeader("success");
                    var status = jqXHR.status;
                    alert("响应头参数是：" + success + ",状态码是：" + status);
                    if (result == null || result.id == null) {
                        alert("插入失败！");
                        result;
                    }
                    alert("插入成功");
                }
            });

        }

        // post();
        // get();
        // getList();
        // updateUser();
        // updateUserName();
        // deleteUser();
        // get2();
        postStatus();
    </script>
</head>
<body>
<h2>测试restful请求</h2>
</body>
</html>