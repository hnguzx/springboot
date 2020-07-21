<%@ page pageEncoding="UTF-8" language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>国际化消息</title>
</head>
<body>
<a href="./view?language=zh_CN">简体中文</a>
<a href="./view?language=en_US">美国英文</a>
<h2>
    <spring:message code="msg"/>
</h2>

<h2>
    Local:${pageContext.response.locale}
</h2>

</body>
</html>