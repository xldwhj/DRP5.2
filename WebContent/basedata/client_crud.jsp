<%@page import="com.bjpowernode.drp.basedata.manager.ClientManager"%>
<%@page import="com.bjpowernode.drp.basedata.domain.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	//out.println(id);
	Client client = ClientManager.getInstance().findClientOrRegionById(id);
	boolean success =false;
	if("delete".equals(request.getParameter("command"))){
		ClientManager.getInstance().delClientOrRegion(id);
		//out.println("删除成功!");
		success = true;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" href="../style/drp.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>分销商维护</title>
			<script type="text/javascript">
				function modifyClient(){
					window.self.location = "client_modify.jsp?id=<%=id %>";
				}
				
				function detailClient(){
					//点击查看详细按钮，跳转到client_detail.jsp页面，并将当前分销商id传递过去
					window.self.location = "client_detail.jsp?id=<%=id %>";
				}
				
				function deleteClient(){
					if(window.confirm("确认删除该分销商吗？")){
						//alert("删除成功");
						with(document.getElementById("clientForm")){
							action = "client_crud.jsp";
							method = "post";
							submit();
						}
						
					}
				}
			</script>
	</head>

	<body class="body1">
		<form id="clientForm" name="clientForm" method="post" action="">
			<input type="hidden" name = "command" value = "delete"></input>
			<input type="hidden" name = "id" value="<%=id %>" />
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				height="8">
				<tr>
					<td width="522" class="p1" height="2" nowrap="nowrap">
						<img src="../images/mark_arrow_02.gif" width="14" height="14" />
						&nbsp;
						<b>基础数据管理&gt;&gt;分销商维护</b>
					</td>
				</tr>
			</table>
			<hr width="97%" align="center" size="0" />
			<p>
			<p>
			<table width="95%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="213">
						<div align="right">
							当前分销商名称：
						</div>
					</td>
					<td width="410">
						<label>
							<input name="clientName" type="text" class="text1"
								id="clientName" readonly="true" value="<%=client.getName() %>">
						</label>
					</td>
				</tr>
			</table>
			<p>
				<label>
					<br />
				</label>
			<hr />
			<p align="center">
				<input name="btnModifyClient" type="button" class="button1"
					id="btnModifyClient" onClick="modifyClient()" value="修改分销商" />
				&nbsp;
				<input name="btinDeleteClient" type="button" class="button1"
					id="btinDeleteClient" value="删除分销商" onclick="deleteClient()" />
				&nbsp;
				<input name="btnViewDetail" type="button" class="button1"
					id="btnViewDetail" onClick="detailClient()"
					value="查看详细信息" />
			</p>
		</form>
	</body>
</html>
<%
	if(success){
%>
<!--添加成功后，在界面弹出界面  -->
<script type="text/javascript">
	alert("成功删除一个分销商！");
	window.parent.clientTreeFrame.location.reload();//从两个帧节点的父节点重新加载该界面
</script>
<%
	}
%>