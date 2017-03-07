<%@page import="com.bjpowernode.drp.sysmgr.manager.UserManager"%>
<%@page import="com.bjpowernode.drp.sysmgr.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request.setCharacterEncoding("UTF-8");
	//使用隐含域判断是否点击提交按钮
	String userId = "";
	String userName = "";
	String password = "";
	String contactTel = "";
	String email = "";
    String command = request.getParameter("command");
    if("add".equals(command)){
    	User user = UserManager.getInstance().findUserById(request.getParameter("userId"));
    	if(user == null){
    		user = new User();
    		userId = request.getParameter("userId");
    		userName = request.getParameter("userName");
    		password = request.getParameter("password");
    		contactTel = request.getParameter("contactTel");
    		email = request.getParameter("email");
    		user.setUserId(request.getParameter("userId"));
        	user.setUserName(request.getParameter("userName"));
        	user.setPassword(request.getParameter("password"));
        	user.setContactTel(request.getParameter("contactTel"));
        	user.setEmail(request.getParameter("email"));
        	//User user = UserManager.getInstance().findUserById(request.getParameter("userId"));
        	UserManager.getInstance().addUser(user);
        	out.println("添加用户成功");
    	}
    	else{
    		out.println("已存在用户代码为" + request.getParameter("userId") + "的用户！");
    		//return;
    		userId = request.getParameter("userId");
    		userName = request.getParameter("userName");
    		password = request.getParameter("password");
    		contactTel = request.getParameter("contactTel");
    		email = request.getParameter("email");
    	}
    	
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加用户</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
	function goBack() {
		window.self.location="user_maint.jsp"
	}
	
	function addUser() {
		var userIdField = document.getElementById("userId");
		//用户代码不能为空
		if(trim(userIdField.value) == ""){
			alert("用户代码不能为空！");
			userIdField.focus();//在文本框获得焦点
			return;
		}
		//用户代码不能小于4位
		if(trim(userIdField.value).length < 4){
			alert("用户代码不能少于4位！");
			userIdField.focus();//在文本框获得焦点
			return;
		}
		
	
		//采用正则表达式判断用户代码只能是数字或字母，为4~6位（中午作业）
		var re = new RegExp(/^[a-zA-Z0-9]{4,6}$/);
		if (!re.test(trim(userIdField.value))) {
			alert("用户代码必须为数字或字母,只能为4~6位！");
			userIdField.focus();
			return;
		}
		
		var userNameField = document.getElementById("userName");
		if(trim(userNameField.value)==""){
			alert("用户名不能为空！");
			userNameField.focus();//在文本框获得焦点
			return;	
		}
		
		var passwordField = document.getElementById("password");
		if(trim(passwordField.value) == ""){
			alert("密码不能为空！");
			passwordField.focus();//在文本框获得焦点
			return;
		}
		//用户代码不能小于4位
		if(trim(passwordField.value).length < 6){
			alert("用户密码不能少于6位！");
			passwordField.focus();//在文本框获得焦点
			return;
		}
		
		var contactTelField = document.getElementById("contactTel");
		if(trim(contactTelField.value)!= ""){
			if(!trim(contactTelField.value).match("^[0-9]*$") || trim(contactTelField.value).length != 11){
				alert("电话号码格式不正确！");
				contactTelField.focus();//在文本框获得焦点
				return;
			}
		}
		
		var emailField = document.getElementById("email");
		if(trim(emailField.value)!=""){
			if(emailField.value.indexOf("@") <= 0 || emailField.value.indexOf("@") == emailField.value.length - 1){
				alert("邮箱格式不正确！")
				emailField.focus();
				return;
			}
		}
		//alert("a"+ document.getElementById("spanUserId").innerHTML + "a");
		if(document.getElementById("spanUserId").innerHTML != ""){
			alert("用户代码已存在！");
			document.getElementById("userId").focus();
			return;
		}
		/*
		document.getElementById("userForm").action="user_add.jsp";//相当于form中的action="user_add.jsp"
		document.getElementById("userForm").method="post";//相当于form中的method="post"
		document.getElementById("userForm").submit();//可以把type="button"换成type="submit"
		*/
		//采用with关键字等同于上面的写法
		with(document.getElementById("userForm")){
			action="user_add.jsp";
			method="post";
			submit();
		}
	}
	
	//页面加载时获取焦点
	function init(){
		document.getElementById("userId").focus();
	}
	
	function userIdOnKeyPress(){
		//window.event.keyCode;获取按键的字面值
		if(!(window.event.keyCode >=97 && window.event.keyCode<=122)){
			event.keyCode = 0;
		}
	}
	
	/*var xmlhttp;
	
	function creatXMLHttpRequest() {
		//表示当前浏览器不是IE
		if(window.XMLHttpRequest){
			xmlHttp = new XMLHttpRequest();
		}else if(window.ActiveXObject){
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	*/
	
	//焦点离开响应的方法
	function validate(){
		var xmlhttp = null;
		
		if(trim(document.getElementById("userId").value).length != 0){
			//创建Ajax核心对象XMLHttpRequest
			//creatXMLHttpRequest();
			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest();
			}else if(window.ActiveXObject){
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			var url="user_validate.jsp?userId=" + trim(document.getElementById("userId").value) + "&time=" + new Date().getTime();//防止产生缓存
			/*
			　第一个参数定义发送请求所使用的方法（GET 还是 POST）。
			    与 POST 相比，GET 更简单也更快，并且在大部分情况下都能用。
			   然而，在以下情况中，请使用 POST 请求：
			(1)无法使用缓存文件（更新服务器上的文件或数据库）
			(2)向服务器发送大量数据（POST 没有数据量限制）
			(3)发送包含未知字符的用户输入时，POST 比 GET 更稳定也更可靠
　　                   	第二个参数规定服务器端脚本的 URL(该文件可以是任何类型的文件，比如 .txt 和 .xml，或者服务器脚本文件，比如 .asp 和 .php （在传回响应之前，能够在服务器上执行任务）)。
			第三个参数规定应当对请求进行异步地处理(true（异步）或 false（同步）)。
			本文设置的method为get，设置请求的url，设置为异步提交
			*/
			xmlHttp.open("get",url,true);
			//将方法地址复制给onreadystatechange属性，类似于消息接收地址
			//xmlHttp.onreadystatechange = callback;使用匿名函数代替
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4){
					//200代表请求Tomcat响应成功，其他的都是失败404，500页面什么的
					if(xmlHttp.status == 200){
						if(trim(xmlHttp.responseText) != ""){
							document.getElementById("spanUserId").innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>"
						}else{
							document.getElementById("spanUserId").innerHTML = "";
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
			document.getElementById("spanUserId").innerHTML = "";
		}
		
	}
	
	function callback(){
		/*readyState有5个状态，4代表有响应
		0描述一种"未初始化"状态；此时，已经创建一个XMLHttpRequest对象，但是还没有初始化。在调用open()之前
		1描述一种"发送"状态；此时，代码已经调用了XMLHttpRequest open()方法并且XMLHttpRequest已经准备好把一个请求发送到服务器。
		2描述一种"发送"状态；此时，已经通过send()方法把一个请求发送到服务器端，但是还没有收到一个响应。
		3描述一种"正在接收"状态；此时，已经接收到HTTP响应头部信息，但是消息体部分还没有完全接收结束。
		4描述一种"已加载"状态；此时，响应已经被完全接收。
		*/
		if(xmlHttp.readyState == 4){
			//200代表请求Tomcat响应成功，其他的都是失败404，500页面什么的
			if(xmlHttp.status == 200){
				if(trim(xmlHttp.responseText) != ""){
					document.getElementById("spanUserId").innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>"
				}else{
					document.getElementById("spanUserId").innerHTML = "";
				}
				
			}else{
				//alert(xmlHttp.status);
				alert("请求失败，错误码 =" + xmlHttp.status);
			}
		}
	}
	
</script>
	</head>

	<body class="body1" onload="init()">
		<form name="userForm" target="_self" id="userForm">
			<input type="hidden" name="command" value="add">
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>&nbsp;
							
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap align="left">
							<img src="../images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>系统管理&gt;&gt;用户维护&gt;&gt;添加</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="45%" height="26">
							<div align="right">
								<font color="#FF0000">*</font>用户代码:&nbsp;
							</div>
						</td>
						<td width="55%" align="left">
							<input name="userId" type="text" class="text1" id="userId"
								size="20" maxlength="10" onkeypress = "userIdOnKeyPress()" value = "<%=userId %>" onblur="validate()">
							<span id="spanUserId"></span>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>用户名称:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="userName" type="text" class="text1" id="userName"
								size="20" maxlength="20" value="<%=userName %>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>密码:&nbsp;
							</div>
						</td>
						<td align="left">
							<label>
								<input name="password" type="password" class="text1"
									id="password" size="21" maxlength="20" value=<%=password %>>
							</label>
						</td>
					</tr>
					<tr>
						<td height="26" >
							<div align="right">
								联系电话:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="contactTel" type="text" class="text1"
								id="contactTel" size="20" maxlength="20" value="<%=contactTel %>">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								email:&nbsp;
							</div>
						</td>
						<td align="left">
							<input name="email" type="text" class="text1" id="email"
								size="20" maxlength="20" value="<%=email %>">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="添加" onClick="addUser()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onClick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
