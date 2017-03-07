<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//有的浏览器存在一些小bug，并不能直接找到错误界面。因此我们在此处重定向一下
		//发生错误时，tomcat将错误码值使用reques.setAttribute("javax.servlet.error.status_code",errorCode)存储
		Integer errorCode =  (Integer)request.getAttribute("javax.servlet.error.status_code");
		if(errorCode.intValue() == 404){
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}else if(errorCode.intValue() == 500){
			response.sendRedirect(request.getContextPath() + "/500.jsp");
		}
	%>
</body>
</html>