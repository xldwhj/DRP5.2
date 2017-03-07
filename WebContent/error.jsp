<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <!--   此处需要设置isErrorPage="true",来表明此页面为发生错误后的跳转页面 -->
<%@ page isErrorPage="true" %> 

<html> 
<head> 
<title>错误信息!</title> 
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<style type="text/css">
<!--
.STYLE1 {
	font-size: 24px;
	font-weight: bold;
	color: #FF0000;
}
-->
</style>
</head> 
<body> 
<br>
<br>
<center> 
<table width="500">
  <tr>
    <td align="center"><p align="left"><span class="STYLE1">错误信息：</span></td>
  </tr>
</table>
<hr>
<table width="500"> 
<tr> 
<td>
	<%=exception.getMessage()%></td> 
</tr> 
<tr> 
<td align="center" colspan="2"> 
 <p> 
<input type="button" name="goback" value="返回" onClick="javascript:history.go(-1);"> 
</td> 
</tr> 
<tr> 
<td align="right" colspan="2"></td> 
</tr>
<tr>
  <td align="right" colspan="2"></td>
</tr> 
</table> 
</center> 
</body> 
</html>
