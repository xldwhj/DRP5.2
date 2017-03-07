<%@ page import="com.bjpowernode.drp.util.datadict.domain.ItemUnit"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="com.bjpowernode.drp.util.datadict.domain.ItemCategory"%>
<%@ page import="com.bjpowernode.drp.basedata.domain.Item"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	/* Item item = (Item)request.getAttribute("item"); */
%>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改物料</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script typr="text/javascript">
			function validateForm(){
				var message = "";
				if (trim(document.getElementById("itemName").value) == "") {
					message += "物料名称为空，请修改！";
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
		<form name="itemForm" target="_self" id="itemForm" action="servlet/item/ModifyItemServlet" method="post" onsubmit="return validateForm()">
			<div align="center">
				<table width="95%" height="21" border="0" cellpadding="2"
					cellspacing="2">
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
							<b>基础数据管理&gt;&gt;物料维护&gt;&gt;修改</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								物料代码:&nbsp;
							</div>
						</td>
						<td width="78%">
							<input name="itemNo" type="text" class="text1" id="itemNo"
								<%-- size="10" maxlength="10" readonly="true" value=<%=item.getItemNo() %>> --%>
								size="10" maxlength="10" readonly="true" value="${item.itemNo }">
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
								<%-- size="20" maxlength="20" value=<%=item.getItemName() %>> --%>
								size="20" maxlength="20" value="${item.itemName }">
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
									<%-- size="20" maxlength="20" value=<%=item.getSpec() %>> --%>
									size="20" maxlength="20" value="${item.spec }" %>
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
								<%-- size="20" maxlength="20" value=<%=item.getPattern() %>> --%>
								size="20" maxlength="20" value="${item.pattern }">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>类别:&nbsp;
							</div>
						</td>
						<td>
						<select name="category" class="select1" id="category">
						<%-- 	<%
								List<ItemCategory> itemCategoryList = (List)request.getAttribute("itemCategoryList");
							%>
							
								<%
									for(Iterator<ItemCategory> iter = itemCategoryList.iterator();iter.hasNext();){
										ItemCategory ic = iter.next();
										String selectedStr = "";
										if(item.getItemCategory().getId().equals(ic.getId())){
											selectedStr = "selected";
										}
								%> 
									 <option value="<%=ic.getId() %>" <%=selectedStr %>><%=ic.getName() %></option> 
								<%
									}
								%> --%>
								<c:forEach items="${itemCategoryList }" var="itemCategory">
									<c:set var="selectedStr" />
										<c:if test="${item.itemCategory.id eq itemCategory.id}">
											<c:set value="selected" var="selectedStr" />
										</c:if>
										<option value="${itemCategory.id }" ${selectedStr }>${itemCategory.name }</option>
								</c:forEach>
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
						<select name="unit" class="select1" id="unit">
							<%-- <%
								List<ItemUnit> itemUnitList = (List)request.getAttribute("itemUnitList");
							%>
							
								<%
									for(Iterator<ItemUnit> iter = itemUnitList.iterator();iter.hasNext();){
										ItemUnit iu = iter.next();
										String selectedStr = "";
										if(item.getItemUnit().getId().equals(iu.getId())){
											selectedStr = "selected";
										}
								%>
								<option value="<%=iu.getId() %>" <%=selectedStr %>><%=iu.getName() %></option>
								<%
									}
								%> --%>
								<c:forEach items="${itemUnitList }" var="itemUnit">
									<c:set var="selectedStr" />
									<c:if test="${item.itemUnit.id eq  itemUnit.id}">
										<c:set value="selected" var="selectedStr" />
									</c:if>
									<option value="${itemUnit.id }" ${selectedStr }>${itemUnit.name }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnModify" class="button1" type="submit"
						id="btnModify" value="修改">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnModify" class="button1" type="button"
						id="btnModify" value="返回" onClick="history.go(-1)">
				</div>
			</div>
		</form>
	</body>
</html>
