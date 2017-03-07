package com.bjpowernode.drp.util.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.basedata.manager.ClientManager;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
/**
 * 验证分销商代码是否存在
 * @author Administrator
 *
 */
public class ClientIdValidateServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		System.out.println("------------ClientIdValidateServlet.init()------------");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.service(arg0, arg1);
		response.setContentType("text/html;charset=UTF-8");
		String clientId = request.getParameter("clientId");
		boolean flag = ClientManager.getInstance().findClientByClientId(clientId);
		if(flag){
			response.getWriter().print("分销商代码已经存在！");
		}
	}
	
}
