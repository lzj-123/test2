package com.sc.realm;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class CustomFormAuthentiCationFilter extends FormAuthenticationFilter {
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws java.lang.Exception{
		System.out.println("进入了自定义表单过滤器");
		//获取存在session里的验证码答案
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		String validateCode = (String)session.getAttribute("validateCode");
		
		//取出页面的验证码并对比
		String randomcode = req.getParameter("randomcode");
		if(randomcode != null && validateCode != null && !randomcode.equals(validateCode)){
			req.setAttribute("shiroLoginFailure", "randomCode");//设置失败信息
			return true;//拒绝访问，返回登录
		}
		return super.onAccessDenied(request, response);
	}
}
