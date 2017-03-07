<%@page import="com.bjpowernode.drp.sysmgr.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User)session.getAttribute("user_info");
	if(user != null){
		String oldPassword = request.getParameter("oldPassword");
		if(!oldPassword.equals(user.getPassword())){
			out.println("原密码输入错误！");
		}
	}else{
		out.println("<script>alert('用户信息已过期，请重新登录！');</script>");
		response.sendRedirect("login.jsp");
	}
%>