<%@page import="java.util.Iterator"%>
<%@page import="com.bjpowernode.drp.util.datadict.domain.ItemUnit"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bjpowernode.drp.util.datadict.domain.ItemCategory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加物料</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>		
		<script type="text/javascript">
			function validateForm() {
				
				var message = "";
				if (trim(document.getElementById("itemNo").value) == "") {
					message = "物料代码为空，请修改！";
				}
				if (trim(document.getElementById("itemName").value) == "") {
					message += "\n物料名称为空，请修改！";
				}
				if (message != "") {
					alert(message);
					return false;
				} 
				return true;	
			}
		</script>
	</head>

	<body class="body1">
		<!-- onsubmit为阻止表单提交事件，返回值为true时允许表单提交，否则不允许表单提交 -->
		<form name="itemForm" target="_self" id="itemForm" action="servlet/item/AddItemServlet" method="post" onsubmit="return validateForm()">
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;物料维护&gt;&gt;添加</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								<font color="#FF0000">*</font>物料代码:&nbsp;
							</div>
						</td>
						<td width="78%">
							<input name="itemNo" type="text" class="text1" id="itemNo"
								size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>物料名称:&nbsp;
							</div>
						</td>
						<td>
							<input name="itemName" type="text" class="text1" id="itemName"
								size="20" maxlength="20">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								物料规格:&nbsp;
							</div>
						</td>
						<td>
							<label>
								<input name="spec" type="text" class="text1" id="spec"
									size="20" maxlength="20">
							</label>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								物料型号:&nbsp;
							</div>
						</td>
						<td>
							<input name="pattern" type="text" class="text1" id="pattern"
								size="20" maxlength="20">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>类别:&nbsp;
							</div>
						</td>
						<td>
							<%-- <%
								List<ItemCategory> itemCategoryList = (List)request.getAttribute("itemCategoryList");
							%> --%>
							<select name="category" class="select1" id="category">
								
								<%-- <%
									for(Iterator<ItemCategory> iter = itemCategoryList.iterator(); iter.hasNext();){
										ItemCategory ic = iter.next();
								%> --%>
								<c:forEach items="${itemCategoryList }" var="itemCategory">
									<option value=${itemCategory.id }>${itemCategory.name }</option>
								</c:forEach>
								<%-- <%
									}
								%> --%>
							</select>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>计量单位:&nbsp;
							</div>
						</td>
						<td>
							<%-- <%
								List<ItemUnit> itemUnitList = (List)request.getAttribute("itemUnitList");
							%> --%>
							<select name="unit" class="select1" id="unit">
								<%-- <%
									for(Iterator<ItemUnit> iter = itemUnitList.iterator(); iter.hasNext();){
										ItemUnit iu = iter.next();
								%> --%>
								<c:forEach items="${itemUnitList }" var="itemUnit">
								<%-- 	<option value="<%=iu.getId() %>"><%=iu.getName() %></option> --%>
									<option value="${itemUnit.id }">${itemUnit.name }</option>
								</c:forEach>
								<%-- <%
									}
								%> --%>
							</select>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnAdd" class="button1" type="submit" id="btnAdd"
						value="添加">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onClick="history.go(-1)">
				</div>
			</div>
		</form>
	</body>
</html>
