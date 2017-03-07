<%@page import="java.util.Iterator"%>
<%@page import="com.bjpowernode.drp.util.datadict.manager.DataDictManager"%>
<%@page import="com.bjpowernode.drp.util.datadict.domain.ClientLevel"%>
<%@page import="java.util.List"%>
<%@page import="com.bjpowernode.drp.basedata.manager.ClientManager"%>
<%@page import="com.bjpowernode.drp.basedata.domain.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	Client client = ClientManager.getInstance().findClientOrRegionById(id);
	List<ClientLevel> clientLevelList = DataDictManager.getInstance().findClientLevelList();
	if("modify".equals(request.getParameter("command"))){
		client.setName(request.getParameter("clientName"));
		//out.println(request.getParameter("clientLevel"));
		//获取分销商类型对应的client_level_id
		for(Iterator<ClientLevel> iter = clientLevelList.iterator();iter.hasNext();){
			ClientLevel cl = iter.next();
			if(request.getParameter("clientLevel").equals(cl.getId())){
				client.setClientLevel(cl);
			}
		}
		
		client.setBankAcctNo(request.getParameter("bankAcctNo"));
		client.setContaceTel(request.getParameter("contactTel"));
		client.setAddress(request.getParameter("address"));
		client.setZipCode(request.getParameter("zipCode"));
		
		ClientManager.getInstance().modifyClientOrRegion(client);
		out.println("修改成功");
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改分销商</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script language="javascript">
			function modifyClient(){
				var name = document.getElementById("clientName").value;
				if(trim(name) == ""){
					alert("分销商名称不能为空！");
					document.getElementById("clientName").focus();
					return;
				}
				with(document.getElementById("clientForm")){
					action="client_modify.jsp";
					method="post";
					submit();
				}
			}
			
			function goBack(){
				window.self.location = "client_crud.jsp?id=<%=id %>";
			}
			
			function init(){
				document.getElementById("clientName").focus();
			}
		</script>
	</head>

	<body class="body1" onload="init()">
		<form name="clientForm" target="_self" id="clientForm">
			<input type="hidden" name="command" value="modify">
			<input type="hidden" name="id" value="<%=id %>">
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="8">
					<tr>
						<td width="522" class="p1" height="2" nowrap>
							<img src="../images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;分销商维护&gt;&gt;修改分销商</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								分销商代码:&nbsp;
							</div>
						</td>
						<td width="78%">
							<input name="clientId" type="text" class="text1" id="clientId"
								size="10" maxlength="10" readonly="true" value="<%=client.getId() %>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>分销商名称:&nbsp;
							</div>
						</td>
						<td>
							<input name="clientName" type="text" class="text1"
								id="clientName" size="40" maxlength="40" value="<%=client.getName() %>">
						</td>
					</tr>
					<tr>
						<td height="15">
							<div align="right">
								<font color="#FF0000">*</font>分销商类型:&nbsp;
							</div>
						</td>
						<td>
							
							<select name="clientLevel" class="select1" id="clientLevel">
								<%
									for(Iterator<ClientLevel> iter = clientLevelList.iterator();iter.hasNext();){
										ClientLevel cl = iter.next();
										String selectedStr = "";
										if(client.getClientLevel().getId().equals(cl.getId())){
											selectedStr = "selected";
										}
								%>
									<option value="<%=cl.getId() %>" <%=selectedStr %>><%=cl.getName() %></option>
								<%
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								银行帐号:&nbsp;
							</div>
						</td>
						<td>
							<input name="bankAcctNo" type="text" class="text1"
								id="bankAcctNo" size="10" maxlength="10" value="<%=client.getBankAcctNo() %>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系电话:&nbsp;
							</div>
						</td>
						<td>
							<input name="contactTel" type="text" class="text1"
								id="contactTel" size="10" maxlength="11" value="<%=client.getContaceTel() %>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								地址:&nbsp;
							</div>
						</td>
						<td>
							<input name="address" type="text" class="text1" id="address"
								size="10" maxlength="10" value="<%=client.getAddress() %>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								邮编:&nbsp;
							</div>
						</td>
						<td>
							<input name="zipCode" type="text" class="text1" id="zipCode"
								size="10" maxlength="10" value="<%=client.getZipCode() %>">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnModify" class="button1" type="button"
						id="btnModify" value="修改" onclick="modifyClient()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
