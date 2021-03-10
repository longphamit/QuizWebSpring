package com.longpc.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;

import com.longpc.dto.MessageDTO;
import com.longpc.entity.UserEntity;
import com.longpc.util.FieldConstant;
@Order(2)
@WebFilter
public class RoleFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest req=(HttpServletRequest) request;
			HttpServletResponse resp=(HttpServletResponse) response;
			HttpSession session=req.getSession();
			UserEntity userEntity= (UserEntity) session.getAttribute(FieldConstant.USER);
			if(userEntity==null||userEntity.getRole().equals("CUSTOMER")) {
				if(req.getServletPath().contains("/admin")) {
					MessageDTO messageDTO= new MessageDTO();
					messageDTO.setContent("403 - Invalid Role");
					messageDTO.setStatus(false);
					session.setAttribute(FieldConstant.MESSAGE, messageDTO);
					resp.sendRedirect(req.getContextPath()+"/error");
					return;
				}
			}
			chain.doFilter(request, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
