<%--suppress ALL --%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <link type="text/css" rel="stylesheet" href="../jquery-easyui-1.9.7/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="../jquery-easyui-1.9.7/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="../jquery-easyui-1.9.7/demo/demo.css">
    <script type="text/javascript" src="../jquery-easyui-1.9.7/jquery.min.js"></script>
    <script type="text/javascript" src="../jquery-easyui-1.9.7/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        function onSearch() {
            var opts = $("#dg").datagrid("options");
            opts.uri = "./list";
            var userName = $("#userName").val();
            var note = $("#nonte").val();

            var param = {};
            if (userName != null && userName.trim() != '') {
                param.userName = userName;
            }
            if (note != null && note.trim() != '') {
                param.note = note;
            }
            $("#dg").datagrid('load', param);

        }
    </script>
</head>
<body>
<div style="margin: 20px 0"></div>
<div class="layout" style="width: 100%;height: 350px">
    <div data-options="region:'north'" style="height: 50px">
        <form id="searchForm" method="post">
            <table>
                <tr>
                    <td>用户名称</td>
                    <td>
                        <input id="userName" name="userName" class="textbox" data-options="prompt:'输入用户名称...'"
                               style="width: 100%; height: 32px">
                    </td>
                    <td>备注</td>
                    <td>
                        <input id="note" name="note" class="textbox" data-options="prompt:'输入备注...'"
                               style="width: 100%; height: 32px">
                    </td>
                    <td>
                        <a href="#" class="searchbox-button" data-options="iconCls:'icon-search'" style="width: 80px"
                           onclick="onSearch()">查询</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="regio:'center',title:'用户列表',iconCls:'icon-ok'">
        <table id="dg" class="datagrid" data-options="border:false,singleSelect:true,fit:true,fitColumns:true">
            <thead>
            <tr>
                <th data-options="field:'id'" width="80">
                    编号
                </th>
                <th data-options="field:'userName'" width="80">
                    用户名称
                </th>
                <th data-options="field:'note'" width="80">
                    备注
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.userName}</td>
                    <td>${user.note}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>