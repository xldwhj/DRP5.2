<%@page import="com.bjpowernode.drp.basedata.manager.ClientManager"%>
<%@page import="com.bjpowernode.drp.basedata.domain.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//String name = request.getParameter("name");
	int id = Integer.parseInt(request.getParameter("id"));
	//out.println(id);
	Client region = ClientManager.getInstance().findClientOrRegionById(id);
	
	//String name = region.getName();
	//out.println(id);
	if("modify".equals(request.getParameter("command"))){
		region.setName(request.getParameter("name"));
		//out.println(region.getName() + region.getId());
		ClientManager.getInstance().modifyClientOrRegion(region);
		out.println("修改成功");
	}
%>
<html>
	<head>
		<link rel="stylesheet" href="../style/drp.css" />
		<script src="../script/client_validate.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>修改区域节点</title>
		<script type="text/javascript">
	function modifyRegion() {
		var name = document.getElementById("name").value;
		if(trim(name) == ""){
			alert("区域名称不能为空！");
			document.getElementById("name").focus();
			return;
		}
		with(document.getElementById("regionForm")){
			action="client_node_modify.jsp";
			method="post";
			submit();
		}
	}
	
	function goBack() {
		window.self.location = "client_node_crud.jsp?id=<%=id %>";
	}
	
	function init(){
		document.getElementById("name").focus();
	}
</script>
	</head>

	<body class="body1" onload="init()">
		<form id="regionForm" name="regionForm" method="post" action="">
			<input type="hidden" name="command" value="modify">
			<input type="hidden" name="id" value="<%=id %>">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap="nowrap">
						<img src="../images/mark_arrow_03.gif" width="14" height="14" />
						&nbsp;
						<b>基础数据管理&gt;&gt;分销商维护&gt;&gt;修改区域节点</b>
					</td>
				</tr>
			</table>
			<hr width="97%" align="center" size="0" />
			<p></p>
			<p></p>
			<table width="95%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="213">
						<div align="right">
							<font color="#FF0000">*</font>区域名称：
						</div>
					</td>
					<td width="410">
						<label>
							<input name="name" type="text" class="text1" id="name" value="<%=region.getName() %>">
						</label>
					</td>
				</tr>
			</table>
			<p></p>
			<label>
				<br />
			</label>
			<hr />
			<p align="center">
				<input name="btnModify" class="button1" type="button" id="btnModify"
					value="修改" onClick="modifyRegion()" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="btnModify" class="button1" type="button" id="btnModify"
					value="返回" onclick="goBack()" />
			</p>
		</form>
	</body>
</html>
