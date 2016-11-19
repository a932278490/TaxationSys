package com.dsjsys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dsjsys.pojo.Stuff;

/** 
 * @author  lvzheng lz13519680617@gmail.com
 * @date 创建时间�?2016�?6�?24�? 上午11:33:07 
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest serlvetRequest, ServletResponse serlvetResponse,
		FilterChain filterChain) throws IOException, ServletException {
	 			HttpServletRequest httpServletRequest = (HttpServletRequest) serlvetRequest ;
	 			HttpServletResponse httpServletResponse = (HttpServletResponse)serlvetResponse;
	 			HttpSession session = httpServletRequest.getSession();
	 			Stuff loginUser	= (Stuff)session.getAttribute("loginStuff");
	 			if(loginUser == null){
	 				System.out.println("未登录用户?");
	 				httpServletResponse.encodeRedirectURL(httpServletRequest.getRequestURL().toString());
	 				//httpServletRequest.getRequestDispatcher("noLogin.jsp").forward(httpServletRequest, httpServletResponse);
	 				//httpServletResponse.sendRedirect(httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+httpServletRequest.getContextPath()+"/"+"login/refresh");
	  				httpServletResponse.sendRedirect(httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+httpServletRequest.getContextPath()+"/"+"login/index");
	  			}else{
	 				filterChain.doFilter(serlvetRequest, serlvetResponse);
	 			}
	}

	

}
