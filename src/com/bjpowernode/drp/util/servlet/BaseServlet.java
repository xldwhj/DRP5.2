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
 * �ѹ����Ķ������ŵ�������
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
		//ȡ��session�д洢������
		user = (User)request.getAttribute("user_info");
		
		//service��ԭ���Ǹ���method=get����methon=post�ж�ִ��doGet����doPost����
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
		//��ServletContext��ȡ��BeanFactory
		//this.getServletContext()�õ�ȫ�ֱ�����application��
		//ȡ��InitServlet��ʼ��ʱ�洢��beanFactory
		beanFactory = (BeanFactory)this.getServletContext().getAttribute("beanFactory");
		return beanFactory;
	}
}
