<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>jsr-303</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var pojo = {
                id: 1,
                date: '2020-03-10',
                doubleValue: '9999',
                integer: "56",
                range: 111,
                email: "email@QQ.com",
                size: "123asd123123",
                regexp: 'a,b,c,d'
            }

            $.post({
                url: './validator',
                contentType: "application/json",
                data: JSON.stringify(pojo),
                success: function (result) {
                    console.log(result);
                }
            });
        });
    </script>
</head>
<body></body>
</html>