package com.bjpowernode.drp.util.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.bjpowernode.drp.util.BeanFactory;

public class InitServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		/*//启动就可以创建，创建缺省工厂
		BeanFactory beanFactory = new DefaultBeanFactory();
		
		//将工厂放到ServletContext中,即application中
		this.getServletContext().setAttribute("beanFactory", beanFactory);
		System.out.println("InitServlet的init()方法执行！！");*/
		//将抽象工厂放到ServletContext中
		System.out.println("创建系统的BeanFactory");
		BeanFactory beanFactory = BeanFactory.getInstance();
		this.getServletContext().setAttribute("beanFactory", beanFactory);
	}

}
