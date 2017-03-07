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
		
		//����Item����
		Item item = new Item();
		item.setItemNo(itemNo);
		item.setItemName(itemName);
		item.setSpec(spec);
		item.setPattern(pattern);
		
		//�����������
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setId(category);
		item.setItemCategory(itemCategory);
		
		//���������λ
		ItemUnit itemUnit = new ItemUnit();
		itemUnit.setId(unit);
		item.setItemUnit(itemUnit);

		//ItemManager itemManager = new ItemManagerImpl();
		//String errorMessage = "";
		/*try{
			itemManager.addItem(item);
		}catch(ApplicationException e){
			//request.setAttribute("error_message", "�������ʧ�ܣ����ϴ���Ϊ��"+ itemNo + "��");
			//errorMessage = "�������ʧ�ܣ����ϴ���Ϊ��"+ itemNo + "��";
			errorMessage = e.getMessage();
		}
		
		//�ض��򵽲�ѯҳ��,Ϊ��ֹ���ĳ������⣬�ɲ���URLEncoder.encode�����Ľ��б���
		response.sendRedirect(request.getContextPath() + "/basedata/item_maint.jsp?errorMessage="+ URLEncoder.encode(errorMessage,"UTF-8"));*/
		//ת�����Լ���ҳ��
		//response.sendRedirect(request.getContextPath() + "/basedata/item_add.jsp?errorMessage=" + URLEncoder.encode(errorMessage, "UTF-8"));
		//��������ʽ�쳣
		itemManager.addItem(item);
		//response.sendRedirect(request.getContextPath() + "/basedata/item_add.jsp");
		response.sendRedirect(request.getContextPath() + "/servlet/item/SearchItemServlet");
		//ת��
		//request.getRequestDispatcher("/basedata/item_maint.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
