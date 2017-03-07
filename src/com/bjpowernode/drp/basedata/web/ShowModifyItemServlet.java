package com.bjpowernode.drp.basedata.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.basedata.manager.ItemManager;
import com.bjpowernode.drp.basedata.manager.ItemManagerImpl;
import com.bjpowernode.drp.util.datadict.domain.ItemCategory;
import com.bjpowernode.drp.util.datadict.domain.ItemUnit;
import com.bjpowernode.drp.util.datadict.manager.DataDictManager;


public class ShowModifyItemServlet extends AbstractItemServlet{
	/*private ItemManager itemManager;

	public void init() throws ServletException {
		itemManager = new ItemManagerImpl();
	}*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String itemNo = request.getParameter("itemNo");
		//ItemManager itemManager = new ItemManagerImpl();
		Item item = itemManager.findItemById(itemNo);
		//取得物料类别列表
		List<ItemCategory> itemCategoryList = DataDictManager.getInstance().findItemCategoryList();
		//取得计量单位列表
		List<ItemUnit> itemUnitList = DataDictManager.getInstance().findItemUnitList();
		//设置到request
		request.setAttribute("itemCategoryList", itemCategoryList);
		request.setAttribute("itemUnitList", itemUnitList);
		request.setAttribute("item", item);
		request.getRequestDispatcher("/basedata/item_modify.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
