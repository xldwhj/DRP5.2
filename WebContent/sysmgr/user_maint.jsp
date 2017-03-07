<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bjpowernode.drp.sysmgr.domain.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.bjpowernode.drp.util.PageModel"%>
<%@page import="com.bjpowernode.drp.sysmgr.manager.UserManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	
	*/
	//System.out.println("开始运行");
	//实现页数与翻页功能
	int pageNo = 1;
	int pageSize = 2;//pageNo与pageSize代表1页显示2条数据
	session.setAttribute("pageNo","1");
	PageModel<User> pageModel =  UserManager.getInstance().findUserList(pageNo, pageSize);
	//PageModel<User> pageModel = null;
	int sum = UserManager.getInstance().findAllUser();
	int pageSum = 0;
	pageSum = (sum + pageSize -1)/pageSize;//总页数
	///删除用户代码
	String command = request.getParameter("command");
	if("del".equals(command)){
		String[] userIds = request.getParameterValues("selectFlag");//通过名字取到一个字符串数组
		/*
		for(String userId : userIds){
			UserManager.getInstance().deleteByUserId(userId);
		}*/
		
		boolean flag = UserManager.getInstance().deleteByUserId(userIds);
		if(flag == true){
			pageModel =  UserManager.getInstance().findUserList(pageNo, pageSize);
			sum = UserManager.getInstance().findAllUser();
			pageSum = (sum + pageSize -1)/pageSize;
			System.out.println("删除成功！");
		}
		
		//out.println("已经成功删除" + delsum + "条记录");
	}else if("nextPage".equals(command)){
		//out.println("下一页");
		String pag = (String)session.getAttribute("pageNo");
		int i = Integer.parseInt(pag);
		//if(pageNo < pageSum)
		if(i < pageSum)
		{
			//pageNo++;
			pageModel =  UserManager.getInstance().findUserList(pageNo, pageSize);//某一页显示的数据
			//out.println(pageNo);
			//out.println(pageSum);
		}else{
			//out.println("已经是最后一页!");
		}
	}else if("previousPage".equals(command)){
		if(pageNo >1)
		{
			pageNo--;
			pageModel =  UserManager.getInstance().findUserList(pageNo, pageSize);
			//out.println(pageNo);
			//out.println(pageSum);
		}else if(pageNo == 1){
			//out.println(pageNo);
			//out.println(pageSum);
		}
	}
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户维护</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script type="text/javascript">
	
	function addUser() {
		window.self.location = "user_add.jsp";	
	}
	/*
	    修改用户 信息
	*/
	function modifyUser() {
		var selectFlags = document.getElementsByName("selectFlag");//返回一个checkBox数组
		var i = 0;
		var count = 0;
		for(var j=0; j<selectFlags.length;j++){
			if(selectFlags[j].checked == true){
				count++;
				if(count == 2){
					break;
				}
				i = j;
			}
		}
		if(count == 1){
			//alert(selectFlags[i].value);//获得选中行的用户代码
			window.self.location = "user_modify.jsp?userId=" + selectFlags[i].value;
			//window.self.location = "user_modify.jsp";
		}else{
			alert("请选中一条记录进行修改!");
			return;
		}	
	}
	
	function deleteUser() {
		var selectFlags = document.getElementsByName("selectFlag");//返回一个checkBox数组
		var flag = false;
		for(var i=0; i<selectFlags.length; i++){
			if(selectFlags[i].checked){
				flag = true;
				break;
			}
		}
		
		if(!flag){
			alert("请选择需要删除的用户！");
			return;
		}else if(window.confirm("确认删除吗？")){
			
			with(document.getElementById("userform")){
				action = "user_maint.jsp?command=del";
				method = "post";
				submit();
			}
		}
	}
	
	/*
	选中第一列的checkBox下面的全部选中
	*/
	function checkAll(field) {
		var selectFlags = document.getElementsByName("selectFlag");//返回一个checkBox数组
		for(var i=0; i<selectFlags.length; i++){
			//alert(selectFlags[i].value);
			selectFlags[i].checked = field.checked;
		}
	}

	function topPage() {
		
	}
	//上一页的操作
	function previousPage() {
		with(document.getElementById("userform")){
			action="user_maint.jsp?command=previousPage";
			method="post";
			submit();
		}
	}	
	
	//下一页的操作
	function nextPage() {
		with(document.getElementById("userform")){
			action="user_maint.jsp?command=nextPage";
			method="post";
			submit();
		}
	}
	
	function bottomPage() {
		
	}

</script>
	</head>

	<body class="body1">
		<form name="userform" id="userform">
			<div align="center">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="35">
					<tr>
						<td class="p1" height="18" nowrap>&nbsp;
							
						</td>
					</tr>
					<tr>
						<td width="522" class="p1" height="17" nowrap>
							<img src="../images/mark_arrow_02.gif" width="14" height="14">
							&nbsp;
							<b>系统管理&gt;&gt;用户维护</b>
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
			</div>
			<table width="95%" height="20" border="0" align="center"
				cellspacing="0" class="rd1" id="toolbar">
				<tr>
					<td width="49%" class="rd19">
						<font color="#FFFFFF">查询列表</font>
					</td>
					<td width="27%" nowrap class="rd16">
						<div align="right"></div>
					</td>
				</tr>
			</table>
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td width="55" class="rd6">
						<input type="checkbox" name="ifAll" onClick="checkAll(this)">
					</td>
					<td width="119" class="rd6">
						用户代码
					</td>
					<td width="152" class="rd6">
						用户名称
					</td>
					<td width="166" class="rd6">
						联系电话
					</td>
					<td width="150" class="rd6">
						email
					</td>
					<td width="153" class="rd6">
						创建日期
					</td>
				</tr>
				<%
					List<User> userList = pageModel.getList();
					for(Iterator<User> iter = userList.iterator();iter.hasNext();){
						User user = iter.next();
				%>
				<tr>
					<td class="rd8">
						<input type="checkbox" name="selectFlag" class="checkbox1"
							value="<%= user.getUserId()%>">
					</td>
					<td class="rd8">
						<%= user.getUserId()%>
					</td>
					<td class="rd8">
						<%= user.getUserName()%>
					</td>
					<td class="rd8">
							<%= user.getContactTel()%>
					</td>
					<td class="rd8">
						<%= user.getEmail()%>
					</td>
					<td class="rd8">
						<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreateDate())%>
					</td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="95%" height="30" border="0" align="center"
				cellpadding="0" cellspacing="0" class="rd1">
				<tr>
					<td nowrap class="rd19" height="2">
						<div align="left">
							<font color="black">&nbsp;共&nbsp<%=pageSum %>&nbsp页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="black">当前第</font>&nbsp
							<font color="red"><%=pageNo %></font>&nbsp
							<font color="black">页</font>
							<span id="spanUserId"></span>
						</div>
					</td>
					<td nowrap class="rd19">
						<div align="right">
							<input name="btnTopPage" class="button1" type="button"
								id="btnTopPage" value="|&lt;&lt; " title="首页"
								onClick="topPage()">
							<input name="btnPreviousPage" class="button1" type="button"
								id="btnPreviousPage" value=" &lt;  " title="上页"
								onClick="previousPage()">
							<input name="btnNextPage" class="button1" type="button"
								id="btnNextPage" value="  &gt; " title="下页" onClick="nextPage()">
							<input name="btnBottomPage" class="button1" type="button"
								id="btnBottomPage" value=" &gt;&gt;|" title="尾页"
								onClick="bottomPage()">
							<input name="btnAdd" type="button" class="button1" id="btnAdd"
								value="添加" onClick="addUser()">
							<input name="btnDelete" class="button1" type="button"
								id="btnDelete" value="删除" onClick="deleteUser()">
							<input name="btnModify" class="button1" type="button"
								id="btnModify" value="修改" onClick="modifyUser()">
						</div>
					</td>
				</tr>
			</table>
			<p>&nbsp;
				
			</p>
		</form>
	</body>
</html>
