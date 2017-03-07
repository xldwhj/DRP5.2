<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="com.bjpowernode.drp.basedata.domain.Item"%>
<%@ page import="com.bjpowernode.drp.util.PageModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	/* PageModel<Item> pageModel = (PageModel<Item>)request.getAttribute("pageModel"); */
	/* String itemIdOrName = request.getParameter("itemIdOrName") == null ? "" :request.getParameter("itemIdOrName"); */
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>物料维护</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/windows.js"></script>
		<script type="text/javascript">
	function addItem() {
		//window.self.location = "item_add.jsp";
		/* var url = "servlet/item/ShowAddItemServlet";//相对路径
		window.self.location = url; */
		window.self.location = "<%=basePath %>servlet/item/ShowAddItemServlet";
	}
	
	function modifyItem() {
		var selectFlags = document.getElementsByName("selectFlag");
		var i =0;
		var count = 0;
		for(var j=0;j<selectFlags.length;j++){
			if(selectFlags[j].checked == true){
				count++;
				if(count ==2){
					break;
				}
				i = j;
			}
		}
		if(count == 1){
			window.self.location = "<%=basePath %>/servlet/item/ShowModifyItemServlet?itemNo=" + selectFlags[i].value;
		}
		else{
			alert("请选中一条记录进行修改!");
			return;
		}
	}
	
	<%-- function deleteItem() {
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
			with(document.getElementById("itemform")){
				action = "<%=basePath %>servlet/item/DeleteItemServlet";
				method = "post";
				submit();
			} 
		}
	} --%>
	
	function validateForm(form){
		if(window.confirm("是否确认删除？")){
			return true;
		}
		
		return false;
	}
	function topPage() {
		<%-- window.self.location="<%=basePath%>servlet/item/SearchItemServlet?pageNo=<%=pageModel.getTopPageNo()%>&itemIdOrName=<%=itemIdOrName %>"; --%>
		window.self.location="<%=basePath%>servlet/item/SearchItemServlet?pageNo=${pageModel.topPageNo }&itemIdOrName=${param.itemIdOrName }";
	}
	
	function previousPage() {
		<%-- window.self.location="<%=basePath%>servlet/item/SearchItemServlet?pageNo=<%=pageModel.getPreviousPageNo()%>&itemIdOrName=<%=itemIdOrName %>"; --%>
		window.self.location="<%=basePath%>servlet/item/SearchItemServlet?pageNo=${pageModel.previousPageNo }&itemIdOrName=${param.itemIdOrName }";
	}
	
	function nextPage() {
		<%-- window.self.location="<%=basePath%>servlet/item/SearchItemServlet?pageNo=<%=pageModel.getNextPageNo()%>&itemIdOrName=<%=itemIdOrName %>"; --%>
		window.self.location="<%=basePath%>servlet/item/SearchItemServlet?pageNo=${pageModel.nextPageNo }&itemIdOrName=${param.itemIdOrName }";
	}
	
	function bottomPage() {
		<%-- window.self.location="<%=basePath%>servlet/item/SearchItemServlet?pageNo=<%=pageModel.getBottomPageNo()%>&itemIdOrName=<%=itemIdOrName %>"; --%>
		window.self.location="<%=basePath%>servlet/item/SearchItemServlet?pageNo=${pageModel.bottomPageNo }&itemIdOrName=${param.itemIdOrName }";
	}
	
	function checkAll() {
		var selectFlag = document.getElementsByName("selectFlag");
		for(var i=0;i<selectFlag.length;i++){
			selectFlag[i].checked = document.getElementById("ifAll").checked;
		}
	}
	
	function uploadPic4Item() {
		window.self.location = "../../basedata/item_upload.jsp"
	}
</script>
	</head>

	<body class="body1">
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
							<img src="images/mark_arrow_02.gif" alt="我" width="14"
								height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;物料维护</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<form name="itemForm" action="servlet/item/SearchItemServlet" method="post">
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="17%" height="29">
							<div align="left">
								物料代码/名称:
							</div>
						</td>
						<td width="57%">
							<input name="itemIdOrName" type="text" class="text1"
								id="clientId4" size="50" maxlength="50" value="${param.itemIdOrName }">
						</td>
						<td width="26%">
							<div align="left">
								<input name="btnQuery" type="submit" class="button1"
									id="btnQuery" value="查询">
							</div>
						</td>
<%-- 						<%=request.getParameter("errorMessage") %> --%>
					</tr>
					<tr>
						<td height="16">
							<div align="right"></div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="right"></div>
						</td>
					</tr>
				</table>
				</form>
			</div>
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				class="rd1" align="center">
				<tr>
					<td nowrap height="10" class="p2">
						物料信息
					</td>
					<td nowrap height="10" class="p3">
						&nbsp;
					</td>
				</tr>
			</table>
			<form name="itemform" id="itemform" action="servlet/item/DeleteItemServlet" onsubmit = "return validateForm(this)">
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td width="35" class="rd6">
						<input type="checkbox" name="ifAll" id="ifAll" onClick="checkAll()">
					</td>
					<td width="155" class="rd6">
						物料代码
					</td>
					<td width="155" class="rd6">
						物料名称
					</td>
					<td width="155" class="rd6">
						物料规格
					</td>
					<td width="155" class="rd6">
						物料型号
					</td>
					<td width="138" class="rd6">
						类别
					</td>
					<td width="101" class="rd6">
						计量单位
					</td>
				</tr>
<%-- 				<%
					List<Item> itemList = pageModel.getList();
					for(Iterator<Item> iter = itemList.iterator();iter.hasNext();){
						Item item = iter.next();
				%> --%>
				<c:forEach items="${pageModel.list }" var="item">
				<tr>
					<td class="rd8">
						<%-- <input type="checkbox" name="selectFlag" class="checkbox1" value=<%=item.getItemNo() %>> --%>
						<input type="checkbox" name="selectFlag" class="checkbox1" value="${item.itemNo }">
					</td>
					<td class="rd8">
						<a href="#"
							<%-- onClick="window.open('item_detail.html', '物料详细信息', 'width=400, height=400, scrollbars=no');return false"><%=item.getItemNo() %></a> --%>
							onClick="window.open('item_detail.html', '物料详细信息', 'width=400, height=400, scrollbars=no');return false">${item.itemNo }</a>
					</td> 
					<td class="rd8">
						<%-- <%=item.getItemName() %> --%>
						${item.itemName }
					</td>
					<td class="rd8">
						<%-- <%=item.getSpec() %> --%>
						${item.spec }
					</td>
					<td class="rd8">
						<%-- <%=item.getPattern() %> --%>
						${item.pattern }
					</td>
					<td class="rd8">
						<%-- <%=item.getItemCategory().getName() %> --%>
						${item.itemCategory.name }
					</td>
					<td class="rd8">
						<%-- <%=item.getItemUnit().getName() %> --%>
						${item.itemUnit.name }
					</td> 
				</tr>
			<%-- 	<%
					}
				%> --%>
				</c:forEach>
			</table>
			<table width="95%" height="30" border="0" align="center"
				cellpadding="0" cellspacing="0" class="rd1">
				<tr>
					<td nowrap class="rd19" height="2" width="36%">
						<div align="left">
							<%-- <font color="#FFFFFF">&nbsp;共&nbsp<%=pageModel.getTotalPages() %>&nbsp页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
							<font color="#FFFFFF">&nbsp;共&nbsp${pageModel.totalPages }&nbsp页</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="#FFFFFF">当前第</font>&nbsp
							<%-- <font color="#FF0000"><%=pageModel.getPageNo() %></font>&nbsp --%>
							<font color="#FF0000">${pageModel.pageNo }</font>&nbsp
							<font color="#FFFFFF">页</font> 
						</div>
					</td>
					<td nowrap class="rd19" width="64%">
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
								value="添加" onClick="addItem()">
							<input name="btnDelete" class="button1" type="submit"
								id="btnDelete" value="删除">
							<input name="btnModify" class="button1" type="button"
								id="btnModify" value="修改" onClick="modifyItem()">
							<input name="btnUpload" class="button1" type="button"
								id="btnUpload" value="上传图片" onClick="uploadPic4Item()">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
