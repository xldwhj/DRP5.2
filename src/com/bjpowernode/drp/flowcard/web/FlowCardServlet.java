package com.bjpowernode.drp.flowcard.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

import com.bjpowernode.drp.util.servlet.BaseServlet;

/**
 * ����ά��Servlet
 * @author Administrator
 *
 */
public class FlowCardServlet extends BaseServlet {
/*	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����һ�¸���ķ���,������ʾ���ø����servlet
		super.service(request, response);
		//String command = getCommand();
		if("add".equals(getCommand())){
			add(request, response);
		}else if("del".equals(getCommand())){
			
		}else if("modify".equals(getCommand())){
			
		}else{
			
		}
	}*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if("add".equals(getCommand())){
			add(request, response);
		}else if("del".equals(getCommand())){
			
		}else if("modify".equals(getCommand())){
			
		}else{
			showAdd(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request�ض���
		request.getRequestDispatcher("/flowcard/flow_card_add.jsp").forward(request, response);
	}
	/**
	 * ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("FlowCardServlet-->add");
	}
	
	/**
	 * ɾ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	protected void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
