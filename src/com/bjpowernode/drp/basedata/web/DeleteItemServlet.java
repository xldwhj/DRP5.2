package com.bjpowernode.drp.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.basedata.manager.ItemManager;
import com.bjpowernode.drp.basedata.manager.ItemManagerImpl;

public class DeleteItemServlet extends AbstractItemServlet{
	
	/*private ItemManager itemManager;
	
	public void init() throws ServletException{
		itemManager = new ItemManagerImpl();
	}*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] itemNos = request.getParameterValues("selectFlag");
		boolean flag = itemManager.delItem(itemNos);
		if(flag == true){
			response.sendRedirect(request.getContextPath() + "/servlet/item/SearchItemServlet");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
