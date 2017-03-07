package com.bjpowernode.drp.util.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
/**
 * 采用Filter统一处理字符集
 * @author Administrator
 *
 */
public class CharseEncodingFilter implements Filter {
	private FilterConfig filterConfig;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//System.out.println("CharacterEncodingFilter---->>begin");
		String encoding = filterConfig.getInitParameter("encoding");
		request.setCharacterEncoding(encoding);
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;
	}

}
