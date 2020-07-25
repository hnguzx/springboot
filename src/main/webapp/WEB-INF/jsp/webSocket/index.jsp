<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>webSocket</title>
    <script type="text/javascript" src="../jquery-easyui-1.9.7/jquery.min.js"></script>
    <script type="text/javascript" src="../static/js/webSocket.js"></script>
</head>
<body>
<h2>测试WebSocket站点</h2>
<br>
<input type="text" id="message">
<button onclick="sendMessage()">发送消息</button>
<button onclick="closeWebSocket()">关闭连接</button>
<div id="context"></div>
</body>
</html>