package com.sc.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sc.bean.SysPermission;
import com.sc.realm.CustomFormAuthentiCationFilter;
import com.sc.realm.CustomRealmMD5;
import com.sc.service.SysPermissionService;

@Configuration
public class ShiroConfiguration {

	@Resource
	SysPermissionService sysPermissionInfoService;
	
	@Bean
	public CustomRealmMD5 customRealmMD5(){
		CustomRealmMD5 realm=new CustomRealmMD5();
		HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
		matcher.setHashIterations(3);
		matcher.setHashAlgorithmName("md5");
		realm.setCredentialsMatcher(matcher);
		return realm;
	}
	
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		//DefaultWebSessionManager sessionManager=new DefaultWebSessionManager();
		//sessionManager.setGlobalSessionTimeout(600000);
		//sessionManager.setDeleteInvalidSessions(true);
		//securityManager.setSessionManager(sessionManager);
		
		securityManager.setRealm(customRealmMD5());
		return securityManager;
	}
	
	/**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(){
		CustomFormAuthentiCationFilter formAuthenticationFilter = new CustomFormAuthentiCationFilter();
		formAuthenticationFilter.setLoginUrl("/loginctlr/loginFail.do");
		formAuthenticationFilter.setUsernameParam("uname");
		formAuthenticationFilter.setPasswordParam("upass");
		formAuthenticationFilter.setRememberMeParam("remenberMe");
		
		ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		shiroFilter.setLoginUrl("/login.jsp");
		shiroFilter.setSuccessUrl("/loginctlr/loginS.do");
		shiroFilter.setUnauthorizedUrl("/refuse.jsp");
		
		Map<String, Filter> map=new HashMap<String, Filter>();
		map.put("authc", formAuthenticationFilter);
		
		LogoutFilter logoutFilter=new LogoutFilter();
		logoutFilter.setRedirectUrl("/login.jsp");
		map.put("logout", logoutFilter);
		
		PermissionsAuthorizationFilter permsFilter=new PermissionsAuthorizationFilter(); 
		map.put("perms", permsFilter);
		shiroFilter.setFilters(map);
		
		Map<String, String> filterChainDefinitionMap=new LinkedHashMap<String, String>();
		//静态资源可匿名访问
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/lib/**", "anon");
		filterChainDefinitionMap.put("/schedule/**", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/temp/**", "anon");
		filterChainDefinitionMap.put("/upload/**", "anon");
		filterChainDefinitionMap.put("/MyJsp.jsp", "anon");
		filterChainDefinitionMap.put("/validatecode.jsp", "anon");
		filterChainDefinitionMap.put("/login.jsp", "anon");
		filterChainDefinitionMap.put("/index.jsp", "anon");
		filterChainDefinitionMap.put("/table.jsp", "anon");
		filterChainDefinitionMap.put("/loginctlr/checkUname.do", "anon");
		filterChainDefinitionMap.put("/logout.do", "logout");

	
		
		//设置功能权限 
		List<SysPermission> list=sysPermissionInfoService.selectAllSysPermission();
		for(SysPermission sysPermission : list) {
			if(sysPermission.getPercode()!=null&&!sysPermission.getPercode().equals("")
				&&sysPermission.getUrl()!=null&&!sysPermission.getUrl().equals("")	){
				System.out.println("===1=="+sysPermission.getUrl()+"====="+sysPermission.getPercode());
				filterChainDefinitionMap.put(sysPermission.getUrl(), "perms["+sysPermission.getPercode()+"]");
			}
		}
		
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilter;
	}
}
