<%@page import="com.bjpowernode.drp.basedata.manager.ClientManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="../style/drp.css">
		<style type="text/css">
<!--
a:link {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;
}
a:visited {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;
	
}
a:hover {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;

}
a:active {
	text-decoration: none;
	color: #000000;
	font-size: 9pt;
	font-family: 宋体;
}
-->
</style>

<script type="text/javascript">
	function display(id) {
		 eval("var div=div"+id);
		 eval("var img=img"+id);
		 eval("var im=im"+id);
		 div.style.display=div.style.display=="block"?"none":"block";
		 img.src=div.style.display=="block"?"../images/minus.gif":"../images/plus.gif";
		 im.src=div.style.display=="block"?"../images/openfold.gif":"../images/closedfold.gif";
		 img.alt=div.style.display=="block"?"关闭":"展开";
	}
</script>
	</head>
	<body class="body1">
		<table>
			<tr>
				<td valign="top" nowrap="nowrap">
					<%=ClientManager.getInstance().getClientTreeHTMLString() %>
				</td>
			</tr>
		</table>
	</body>
</html>

