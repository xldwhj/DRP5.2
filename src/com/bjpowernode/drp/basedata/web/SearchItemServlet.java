package com.bjpowernode.drp.basedata.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.PageModel;

public class SearchItemServlet extends AbstractItemServlet {
/*public class SearchItemServlet extends HttpServlet {
	private ItemManager itemManager;

	public void init() throws ServletException {
		itemManager = new ItemManagerImpl();
	}
*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//super.doGet(req, resp);
		//分页信息从配置文件中读取
		ServletConfig config = getServletConfig();
		int pageNo = Integer.parseInt(config.getInitParameter("pageNo"));
		int pageSize = Integer.parseInt(config.getInitParameter("pageSize"));
		//从application范围内取得pageSize，application指的是ServletContext对象
		//String PageSize = this.getServletContext().getInitParameter("pageSize");
	    /*int pageNo = 1;
		int pageSize = 2;*/
		String pageNoString = request.getParameter("pageNo");
		if(pageNoString != null && !"".equals(pageNoString)){
			pageNo = Integer.parseInt(pageNoString);
		}
		
		String itemIdOrName = request.getParameter("itemIdOrName");
		
		//ItemManager itemManager = new ItemManagerImpl();
		PageModel<Item> pageModel = itemManager.findItemList(pageNo, pageSize, itemIdOrName);
		
		request.setAttribute("pageModel", pageModel);
		//转发
		request.getRequestDispatcher("/basedata/item_maint.jsp").forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}