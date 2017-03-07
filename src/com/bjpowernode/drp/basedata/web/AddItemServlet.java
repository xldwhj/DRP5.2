package com.bjpowernode.drp.basedata.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.datadict.domain.ItemCategory;
import com.bjpowernode.drp.util.datadict.domain.ItemUnit;

public class AddItemServlet extends AbstractItemServlet {
	
/*	private ItemManager itemManager;

	public void init() throws ServletException {
		itemManager = new ItemManagerImpl();
	}*/

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charSet=UTF-8");*/
		String itemNo = request.getParameter("itemNo");
		String itemName = request.getParameter("itemName");
		String spec = request.getParameter("spec");
		String pattern = request.getParameter("pattern");
		String category = request.getParameter("category");
		String unit = request.getParameter("unit");
		
		//构造Item对象
		Item item = new Item();
		item.setItemNo(itemNo);
		item.setItemName(itemName);
		item.setSpec(spec);
		item.setPattern(pattern);
		
		//构造物料类别
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setId(category);
		item.setItemCategory(itemCategory);
		
		//构造计量单位
		ItemUnit itemUnit = new ItemUnit();
		itemUnit.setId(unit);
		item.setItemUnit(itemUnit);

		//ItemManager itemManager = new ItemManagerImpl();
		//String errorMessage = "";
		/*try{
			itemManager.addItem(item);
		}catch(ApplicationException e){
			//request.setAttribute("error_message", "添加物料失败，物料代码为【"+ itemNo + "】");
			//errorMessage = "添加物料失败，物料代码为【"+ itemNo + "】";
			errorMessage = e.getMessage();
		}
		
		//重定向到查询页面,为防止中文出现问题，可采用URLEncoder.encode对中文进行编码
		response.sendRedirect(request.getContextPath() + "/basedata/item_maint.jsp?errorMessage="+ URLEncoder.encode(errorMessage,"UTF-8"));*/
		//转发到自己的页面
		//response.sendRedirect(request.getContextPath() + "/basedata/item_add.jsp?errorMessage=" + URLEncoder.encode(errorMessage, "UTF-8"));
		//采用声明式异常
		itemManager.addItem(item);
		//response.sendRedirect(request.getContextPath() + "/basedata/item_add.jsp");
		response.sendRedirect(request.getContextPath() + "/servlet/item/SearchItemServlet");
		//转发
		//request.getRequestDispatcher("/basedata/item_maint.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
