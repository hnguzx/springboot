var webSocket = null;
// 判断游览器是否支持websocket
// if ('WebSocket' in Window) {
    webSocket = new WebSocket("ws://localhost/ws")
// } else {
//     alert("该游览器不支持！")
// }

webSocket.onerror = function () {
    appendMessage("error")
}

/**
 * 连接建立成功的回调方法
 */
webSocket.onopen = function () {
    appendMessage("open")
}

/**
 * 接收到消息的回调方法
 */
webSocket.onmessage = function (event) {
    appendMessage(event.data)
}

/**
 * 连接关闭的回调方法
 */
webSocket.onclose = function () {
    appendMessage("close")
}

/**
 * 监听窗口关闭事件，防止连接还没断开就关闭窗口导致服务端抛异常
 */
window.onbeforeunload = function () {
    webSocket.close();
}

/**
 * 将消息显示在网页上
 * @param message
 */
function appendMessage(message) {
    var context = $("#context").html() + "<br>" + message;
    $("#context").html(context);

}

/**
 * 关闭连接
 */
function closeWebSocket() {
    webSocket.close();
}

/**
 * 发送消息
 */
function sendMessage() {
    var message = $("#message").val();
    // var messageTo = {
    //     name:'guzhixiong',
    //     message:message
    // }
    // messageTo = JSON.stringify(messageTo);
    webSocket.send(message);
}