package com.bjpowernode.drp.basedata.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.bjpowernode.drp.basedata.dao.ItemDao;
import com.bjpowernode.drp.basedata.manager.ItemManager;
import com.bjpowernode.drp.basedata.manager.ItemManagerImpl;
import com.bjpowernode.drp.util.BeanFactory;

/**
 * 
 * @author Administrator
 *
 */
public class AbstractItemServlet extends HttpServlet{
	
	protected ItemManager itemManager;
	//创建Servlet实例时，调用该方法初始化Servlet资源
	public void init() throws ServletException {
		/*itemManager = new ItemManagerImpl();*/
		/*BeanFactory beanFactory = (BeanFactory)this.getServletContext().getAttribute("beanFactory");*/
	/*	itemManager = (ItemManagerImpl)beanFactory.getBean("itemManager");
		ItemDao itemDao = (ItemDao)beanFactory.getBean("itemDao");
		itemManager.setItemDao(itemDao);*/
		
		//itemManager = (ItemManager)BeanFactory.getInstance().getServiceObject("itemManager");
		
		BeanFactory beanFactory = (BeanFactory)this.getServletContext().getAttribute("beanFactory");
		itemManager = (ItemManager)beanFactory.getServiceObject(ItemManager.class);
	}
}
