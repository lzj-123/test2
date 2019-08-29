package com.sc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sc.bean.Message;
import com.sc.bean.SysUser;
import com.sc.bean.Users;
import com.sc.service.SysUserService;

@Controller
@RequestMapping("/loginctlr")
public class LoginController {
	@Autowired
	SysUserService userService;
	
	@RequestMapping("checkUname.do")
	@ResponseBody
	public Message checkUname(Users u){
		System.out.println("获取的用户名："+u.getUname());
		
		Message m =null;
		SysUser login = userService.login(u.getUname());//根据用户名获取用户对象，如果有就表是用户名已存在
		if(login != null){
			m = new Message("200", "yes", "用户名已存在");
		}else{
			m = new Message("100", "no", "用户名可使用");
		}
		
		return m;
	}
	
	
	@RequestMapping("/loginFail.do")
	public ModelAndView loginFail(ModelAndView mv,HttpServletRequest req){
		System.out.println("登录失败");
		
		String msg = (String)req.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		System.out.println("失败信息是:"+msg);
		
		String fw = "";
		
		if(msg != null){
			if(UnknownAccountException.class.getName().equals(msg)){
				fw="nouser";
			} else if(IncorrectCredentialsException.class.getName().equals(msg)){
				fw="pass";
			} else if("randomCode".equals(msg)){
				fw="codeerror"; //验证码错误
			} else{
				fw = "unknown";//未知错误
			}
		}
		mv.setViewName("redirect:../login.jsp?relogin="+fw+"");
		return mv;
	}
	
	
	@RequestMapping("/loginS.do")
	public ModelAndView loginS(ModelAndView mv,HttpSession session){
		System.out.println("认证成功");
		
		Subject subject = SecurityUtils.getSubject();
		
		SysUser principal = (SysUser)subject.getPrincipal();
		
		session.setAttribute("nowuser", principal);
		mv.setViewName("redirect:../index.jsp");
		return mv;
	}
	
	
	
}
