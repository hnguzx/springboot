<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>webSocket</title>
    <script type="text/javascript" src="../jquery-easyui-1.9.7/jquery.min.js"></script>
    <script type="text/javascript" src="../static/js/socket.min.js"></script>
    <script type="text/javascript" src="../static/js/stomp.js"></script>
    <script type="text/javascript">
        var stompClient = null;

        /**
         * 设置连接相关
         */
        function setConnected(connected) {
            $("#connect").attr({"disabled": connected});
            $("#disconnect").attr({"disabled": !connected});

            if (connected) {
                $("#conversationDiv").show();
            } else {
                $("#conversationDiv").hide();
            }

            $("#response").html("");
        }

        /**
         * 开启socket连接
         */
        function openConnect() {
            // 定义请求服务器的端点
            // var url = 'http://'+window.location.host + '/request/socket';
            // var socket = new SockJS('/socket'); // 开启websoket
            var socket = new SockJS('/socket'); // 开启websoket
            stompClient = Stomp.over(socket); // 创建stomp客户端

            stompClient.connect({}, function (frame) {
                setConnected(true); // 连接stomp端点
            })

        }

        /**
         * 断开连接
         */
        function disConnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("DisConnected");

        }

        function sendMsg() {
            var value = $("#message").val();
            stompClient.send("/request/send", {}, value)
        }

        openConnect();
    </script>
</head>
<body>
<h2>发送消息</h2>
<div>
    <div>
        <button id="connect" onclick="openConnect()">连接</button>
        <button id="disconnect" onclick="disConnect()" disabled="disabled">断开连接</button>
    </div>
    <div id="conversationDiv">
        <p>发送内容</p>
        <p>
            <textarea rows="5" id="message"></textarea>
        </p>
        <button id="sendMsg" onclick="sendMsg()">Send</button>
        <p id="response"></p>
    </div>
</div>
</body>
</html>