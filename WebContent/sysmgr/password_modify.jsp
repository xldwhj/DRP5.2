<%@page import="com.bjpowernode.drp.sysmgr.manager.UserManager"%>
<%@page import="com.bjpowernode.drp.sysmgr.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String oldPassword = "";
	String affirmNewPassowrd = "";
	String newPassword = "";
	if("modify".equals(request.getParameter("command"))){
		oldPassword = request.getParameter("oldPassword");
		affirmNewPassowrd = request.getParameter("affirmNewPassowrd");
		newPassword = request.getParameter("newPassword");
		User user = (User)session.getAttribute("user_info");
		String userId = user.getUserId();
		boolean flag = UserManager.getInstance().updateUserById(newPassword, userId);
		if(flag == true){
			out.println("用户密码修改成功，请重新登录！");
			user.setPassword(newPassword);
			session.setAttribute("user_info", user);
		}
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改密码</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
		
	function init(){
		//userForm.oldPassword.focus();
		document.getElementById("oldPassword").focus();
	}
	
	function modifyPasword() {
		var oldPasswordField = document.getElementById("oldPassword");
		if(trim(oldPasswordField.value) == ""){
			alert("请输入原密码！");
			oldPasswordField.focus();//在文本框获得焦点
			return;
		}
		
		var newPasswordField = document.getElementById("newPassword");
		if(trim(newPasswordField.value) == ""){
			alert("请输入新密码！");
			newPasswordField.focus();//在文本框获得焦点
			return;
		}
		
		var affirmNewPassowrdField = document.getElementById("affirmNewPassowrd");
		if(trim(affirmNewPassowrdField.value) == ""){
			alert("请再次输入新密码！");
			affirmNewPassowrdField.focus();//在文本框获得焦点
			return;
		}
		//alert(newPasswordField.value);
		//alert(affirmNewPassowrdField.value);
		if(newPasswordField.value != affirmNewPassowrdField.value){
			alert("两次输入的新密码不一致！");
			return;
		}
		
		if(document.getElementById("spanOldPassword").innerHTML != ""){
			alert("原密码输入错误！");
			document.getElementById("oldPassword").focus();
			return;
		}
		with(document.getElementById("userForm")){
			action="password_modify.jsp";
			method="post";
			submit();
		}
		
	}
	
	function validate(){
		var xmlhttp = null;
		if(trim(document.getElementById("oldPassword").value) !=""){
			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest();
			}else if(window.ActiveXObject){
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			var url="password_validate.jsp?oldPassword=" + trim(document.getElementById("oldPassword").value) + "&time=" + new Date().getTime();//防止产生缓存
			xmlHttp.open("get",url,true);
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4){
					//200代表请求Tomcat响应成功，其他的都是失败404，500页面什么的
					if(xmlHttp.status == 200){
						if(trim(xmlHttp.responseText) != ""){
							document.getElementById("spanOldPassword").innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>"
						}else{
							document.getElementById("spanOldPassword").innerHTML = "";
						}
						
					}else{
						alert("请求失败，错误码 =" + xmlHttp.status);
					}
				}
			}
			//将设置信息发送到Ajax引擎
			xmlHttp.send(null);
		}
		else{
			document.getElementById("spanOldPassword").innerHTML = "";
		}
	}
	
	

</script>
	</head>

	<body class="body1" onload=init()>
		<form name="userForm" id="userForm">
		<input type="hidden" name="command" value="modify">
			<div align="center">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					height="51">
					<tr>
						<td class="p1" height="16" nowrap>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="p1" height="35" nowrap>
							&nbsp&nbsp
							<img src="../images/mark_arrow_02.gif" width="14" height="14">
							<b><strong>系统管理&gt;&gt;</strong>修改密码</b>
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
				<table width="50%" height="91" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="30">
							<div align="right">
								<font color="#FF0000">*</font>原密码:
							</div>
						</td>
						<td>
							<input name="oldPassword" type="password" class="text1"
								id="oldPassword" size="25" onblur="validate()">
							<span id="spanOldPassword"></span>
						</td>
					</tr>
					<tr>
						<td height="27">
							<div align="right">
								<font color="#FF0000">*</font>新密码:
							</div>
						</td>
						<td>
							<input name="newPassword" type="password" class="text1"
								id="newPassword" size="25">
						</td>
					</tr>
					<tr>
						<td height="34">
							<div align="right">
								<font color="#FF0000">*</font>确认密码:
							</div>
						</td>
						<td>
							<input name="affirmNewPassowrd" type="password" class="text1"
								id="affirmNewPassowrd" size="25">
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
				<p>
					<input name="btnModify" class="button1" type="button"
						id="btnModify" value="修改" onClick="modifyPasword()">
					&nbsp; &nbsp;&nbsp;
				</p>
			</div>
		</form>
	</body>
</html>
