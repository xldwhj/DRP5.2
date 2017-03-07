<%@page import="com.bjpowernode.drp.util.datadict.domain.ClientLevel"%>
<%@page import="com.bjpowernode.drp.basedata.manager.ClientManager"%>
<%@page import="com.bjpowernode.drp.basedata.domain.Client"%>
<%@page import="com.bjpowernode.drp.util.IdGenerator"%>
<%@page import="com.bjpowernode.drp.basedata.domain.Region"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String regionName="";
	boolean success = false;
	int pid = Integer.parseInt(request.getParameter("pid"));
	if("addRegion".equals(request.getParameter("command"))){
		Client client = new  Client();
		ClientLevel clientLevel = new ClientLevel();
		//int id = IdGenerator.generatorValue("t_clent");
		client.setId(IdGenerator.generatorValue("t_client"));
		client.setPid(pid);
		client.setClientLevel(clientLevel);
		regionName = request.getParameter("name");
		client.setName(request.getParameter("name"));
		client.setIsLeaf("Y");
		client.setIsClient("N");
		ClientManager.getInstance().addRegionOrClient(client);
		//out.println("成功添加一个区域！");
		success = true;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" href="../style/drp.css" />
		<script src="../script/client_validate.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>添加区域节点</title>
		<script type="text/javascript">
		
			function addRegion(){
				var field = document.getElementById("name");
				if(trim(field.value) == ""){
					alert("用户名不能为空！");
					return;
				}
				with(document.getElementById("regionForm")){
					action = "client_node_add.jsp?pid=" + <%=pid %>;
					method = "post";
					submit();
				}
			}
			
			function init(){
				document.getElementById("name").focus();
			}
		</script>
	</head>

	<body class="body1" onload="init()">
		<form name="regionForm" id="regionForm" method="post" action="">
			<input type="hidden" name="command" value="addRegion" />
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap="nowrap">
						<img src="../images/mark_arrow_03.gif" width="14" height="14" />
						&nbsp;
						<b>基础数据管理&gt;&gt;分销商维护&gt;&gt;添加区域节点</b>
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
							<input name="name" type="text" class="text1" id="name" value="<%=regionName %>"/>
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
				<input name="btnAdd" class="button1" type="button" value="添加" onclick="addRegion()"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="btnBack" class="button1" type="button" value="返回"
					onclick="history.go(-1)" />
			</p>
		</form>
	</body>
</html>
<%
	if(success){
%>
<!--添加成功后，在界面弹出界面  -->
<script type="text/javascript">
		alert("成功添加一个区域！");
</script>
<%
	}
%>