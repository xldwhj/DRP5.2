package com.bjpowernode.drp.util.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.sysmgr.domain.User;
import com.bjpowernode.drp.util.BeanFactory;
/**
 * 把公共的东西都放到该类中
 * @author Administrator
 *
 */
public class BaseServlet extends HttpServlet {
	private String command;
	
	private User user;
	
	private BeanFactory beanFactory;
	
	protected final void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		command = request.getParameter("command");
		//取得session中存储的内容
		user = (User)request.getAttribute("user_info");
		
		//service的原理是根据method=get或者methon=post判断执行doGet或者doPost方法
		super.service(request, response);
	}

	protected String getCommand() {
		return command;	
	}
	
	protected User getUser() {
		return user;
	}
	
	protected BeanFactory getBeanFactory(){
		//beanFactory = BeanFactory.getInstance();
		//从ServletContext中取出BeanFactory
		//this.getServletContext()得到全局变量（application）
		//取得InitServlet初始化时存储的beanFactory
		beanFactory = (BeanFactory)this.getServletContext().getAttribute("beanFactory");
		return beanFactory;
	}
}
