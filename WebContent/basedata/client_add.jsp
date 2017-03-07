<%@page import="com.bjpowernode.drp.basedata.manager.ClientManager"%>
<%@page import="com.bjpowernode.drp.util.IdGenerator"%>
<%@page import="com.bjpowernode.drp.basedata.domain.Client"%>
<%@page import="com.sun.crypto.provider.RSACipher"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.bjpowernode.drp.util.datadict.manager.DataDictManager"%>
<%@page import="com.bjpowernode.drp.util.datadict.domain.ClientLevel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ClientLevel> clientLevelList = DataDictManager.getInstance().findClientLevelList();
	//配置从根目录开始查找
	//String path = request.getContextPath();
	//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String clientId = "";
	String clientName = "";
	String clientLevelId = "";
	String bankAcctNo = "";
	String contactTel = "";
	String address = "";
	String zipCode = "";
	boolean success = false;//设置弹窗条件，弹窗信息为"成功添加一个分销商！"
	int pid = Integer.parseInt(request.getParameter("pid"));
	if("addClient".equals(request.getParameter("command"))){
		Client client = new  Client();
		ClientLevel clientLevel = new ClientLevel();
		clientLevelId = request.getParameter("clientLevel");
		clientLevel.setId(clientLevelId);
		client.setId(IdGenerator.generatorValue("t_client"));
		client.setPid(pid);
		client.setClientLevel(clientLevel);
		clientName = request.getParameter("clientName");
		client.setName(clientName);
		clientId = request.getParameter("clientId");
		client.setClientId(clientId);
		bankAcctNo = request.getParameter("bankAcctNo");
		client.setBankAcctNo(bankAcctNo);
		contactTel = request.getParameter("contactTel");
		client.setContaceTel(contactTel);
		address = request.getParameter("address");
		client.setAddress(address);
		zipCode = request.getParameter("zipCode");
		client.setZipCode(zipCode);
		client.setIsLeaf("Y");
		client.setIsClient("Y");
		ClientManager.getInstance().addRegionOrClient(client);
		success = true;
		//out.println("成功添加一个分销商！");
	}
%>
<html>
	<head>
		<!-- base href=basePath -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加分销商</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
			function validateClientId(){
				var xmlHttp = null;
				if(trim(document.getElementById("clientId").value) != ""){
					if(window.XMLHttpRequest){
						xmlHttp = new XMLHttpRequest();
					}else if(window.ActiveXObject){
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
					//由于base配置，使得servlet/ClientIdValidateServlet代替../servlet/ClientIdValidateServlet起作用
					var url = "../servlet/ClientIdValidateServlet?clientId=" + trim(document.getElementById("clientId").value) + "&time=" + new Date().getTime();//根据web.xml中配置的路径相对于当前的路径../表示回退
					//var url = "servlet/ClientIdValidateServlet?clientId=" + trim(document.getElementById("clientId").value) + "&time=" + new Date().getTime();//根据web.xml中配置的路径相对于当前的路径表示回退
					xmlHttp.open("get",url,true);
					
					xmlHttp.onreadystatechange = function(){
						if(xmlHttp.readyState == 4){
							//200代表请求Tomcat响应成功，其他的都是失败404，500页面什么的
							if(xmlHttp.status == 200){
								if(trim(xmlHttp.responseText) != ""){
									document.getElementById("imgClientName").src = "../images/reset2.gif";
									document.getElementById("spanClientId").innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>"
								}else{
									document.getElementById("spanClientId").innerHTML = "";
									document.getElementById("imgClientName").src = "../images/record.gif";//检验分销商Id可用时在其后显示图片
								}
								
							}else{
								alert("请求失败，错误码 =" + xmlHttp.status);
							}
						}
					}
					//将设置信息发送到Ajax引擎
					xmlHttp.send(null);
				}else{
					document.getElementById("imgClientName").src = "../images/reset2.gif";
					document.getElementById("spanClientId").innerHTML = "分销商代码不能为空！";
				}
			}
			
			function init(){
				document.getElementById("clientId").focus();
			}
			
			function addClient(){
				if(trim(document.getElementById("clientId").value) == ""){
					alert("分销商代码不能为空！");
					return;
				}
				
				if(trim(document.getElementById("clientName").value) == ""){
					alert("分销商名称不能为空！");
					return;
				}
				
				if(document.getElementById("spanClientId").innerHTML != ""){
					alert("分销商代码已存在！");
					document.getElementById("clientId").focus();
					return;
				}
				
				//alert(document.getElementById("clientForm"))
				with(document.getElementById("clientForm")){
					//action = "basedata/client_add.jsp?pid=" + <%=pid %>;
					action = "client_add.jsp?pid=" + <%=pid %>;
					method = "post";
					submit();
				}
				//alert("this is a test!");
			}
		</script>
	</head>

	<body class="body1" onload="init()">
		<form name="clientForm" id="clientForm" method="post" action="">
			<input type="hidden" name="command" value="addClient">
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
							<b>基础数据管理&gt;&gt;分销商维护&gt;&gt;添加分销商</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								<font color="#FF0000">*</font>分销商代码:&nbsp;
							</div>
						</td>
						<td width="78%">
							<input name="clientId" type="text" class="text1" id="clientId"
								size="10" maxlength="10" onblur="validateClientId()" value="<%=clientId %>">
							<img id="imgClientName" src="../images/record.gif">
							<span id="spanClientId"></span>
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
								id="clientName" size="40" maxlength="40" value="<%=clientName %>">
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
										if(clientLevelId != "" && clientLevelId.equals(cl.getId())){
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
								id="bankAcctNo" size="10" maxlength="10" value="<%=bankAcctNo %>">
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
								id="contactTel" size="10" maxlength="10" value="<%=contactTel %>">
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
								size="10" maxlength="10" value="<%=address %>">
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
								size="10" maxlength="10" value="<%=zipCode %>">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="添加" onclick="addClient()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="history.go(-1)" />
				</div>
			</div>
		</form>
	</body>
</html>
<%
	if(success){
%>
<!--添加成功后，在界面弹出界面  -->
<script type="text/javascript">
		alert("成功添加一个分销商！");
</script>
<%
	}
%>