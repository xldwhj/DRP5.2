package com.bjpowernode.drp.util.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.bjpowernode.drp.util.BeanFactory;

public class InitServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		/*//�����Ϳ��Դ���������ȱʡ����
		BeanFactory beanFactory = new DefaultBeanFactory();
		
		//�������ŵ�ServletContext��,��application��
		this.getServletContext().setAttribute("beanFactory", beanFactory);
		System.out.println("InitServlet��init()����ִ�У���");*/
		//�����󹤳��ŵ�ServletContext��
		System.out.println("����ϵͳ��BeanFactory");
		BeanFactory beanFactory = BeanFactory.getInstance();
		this.getServletContext().setAttribute("beanFactory", beanFactory);
	}

}
