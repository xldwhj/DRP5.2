<%@page import="com.bjpowernode.drp.sysmgr.manager.UserManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
		//Thread.currentThread().sleep(5000);
		String userId = request.getParameter("userId");
		//System.out.println(userId);
		if(UserManager.getInstance().findUserById(userId) != null){
			out.println("用户代码已存在！");
		}
		//else{
		//	out.println("");
		//}
	%>