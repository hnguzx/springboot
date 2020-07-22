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
                'userName':'restfulName',
                'sexCode':1,
                'note':'this is note'
            }

            $.post({
                url:'./user',
                contentType:'application/json',
                data:JSON.stringify(params),
                success:function (result) {
                    if (result!=null&& result.id!=null){
                        alert("插入成功！")
                        return;
                    }
                    alert("插入失败！")
                }
            });
        }

        post();
    </script>
</head>
<body>
<h2>测试restful请求</h2>
</body>
</html>