package com.lemon.config;

import com.lemon.shiro.factory.StatelessDefaultSubjectFactory;
import com.lemon.shiro.filter.StatelessAuthcFilter;
import com.lemon.shiro.realm.StatelessRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Shiro配置
 * 
 * @author sjp
 */
@Configuration
public class ShiroConfig {

	private final static String URL_HEAD = "/u";

	/**
	 * 使用FilterRegistrationBean管理DelegatingFilterProxy的生命周期，代替spring项目中shiro在web.xml的配置
	 *
	 * @return FilterRegistrationBean<DelegatingFilterProxy>
	 */
	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy() {
		FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBean = new FilterRegistrationBean<>();
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		filterRegistrationBean.setFilter(proxy);
		// 保留Filter原有的init，destroy方法的调用
		proxy.setTargetFilterLifecycle(true);
		proxy.setTargetBeanName("shiroFilter");
		return filterRegistrationBean;
	}

	/**
	 * 配置shiro filter
	 *
	 * @return ShiroFilterFactoryBean
	 */
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shirFilter() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/**", "statelessAuthcFilter");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		// 自定义拦截器
		Map<String, Filter> filtersMap = new LinkedHashMap<>();
		filtersMap.put("statelessAuthcFilter", new StatelessAuthcFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 配置securityManager
	 *
	 * @return SecurityManager
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置不储存session
		((DefaultSessionStorageEvaluator) ((DefaultSubjectDAO) securityManager.getSubjectDAO())
				.getSessionStorageEvaluator()).setSessionStorageEnabled(false);
		// 设置会话管理器
		securityManager.setSessionManager(defaultSessionManager());
		// 设置无状态SubjectFactory
		securityManager.setSubjectFactory(statelessDefaultSubjectFactory());
		// 设置realm
		LinkedList<Realm> realms = new LinkedList<>();
		realms.add(statelessRealm());
		securityManager.setRealms(realms);
		return securityManager;
	}

	/**
	 * 会话管理器
	 * 
	 * @return DefaultSessionManager
	 */
	@Bean
	public DefaultSessionManager defaultSessionManager() {
		DefaultSessionManager defaultSessionManager = new DefaultSessionManager();
		defaultSessionManager.setSessionValidationSchedulerEnabled(false);
		return defaultSessionManager;
	}

	/**
	 * 无状态SubjectFactory
	 *
	 * @return StatelessDefaultSubjectFactory
	 */
	@Bean
	public StatelessDefaultSubjectFactory statelessDefaultSubjectFactory() {
		return new StatelessDefaultSubjectFactory();
	}

	/**
	 * 配置无状态登陆realm
	 *
	 * @return StatelessRealm
	 */
	@Bean
	public StatelessRealm statelessRealm() {
		StatelessRealm statelessRealm = new StatelessRealm();
		statelessRealm.setCachingEnabled(false);
		return statelessRealm;
	}

	/**
	 * 凭证匹配器
	 * 
	 * @return HashedCredentialsMatcher
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(5);
		return hashedCredentialsMatcher;
	}

	/// @Bean
	// public ShiroDialect shiroDialect() {
	// return new ShiroDialect();
	// }

	/**
	 * 配置 LifecycleBeanPostProcessor. 可以自定的来调用配置在 Spring IOC 容器中 shiro bean 的生命周期方法.
	 *
	 * @return LifecycleBeanPostProcessor
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 启用 IOC 容器中使用 shiro 的注解. 但必须在配置了 LifecycleBeanPostProcessor 之后才可以使用
	 *
	 * @return DefaultAdvisorAutoProxyCreator
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		// true 基于类方法代码，false 基于接口代理
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @return AuthorizationAttributeSourceAdvisor
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager());
		return advisor;
	}

	// @Bean
	// public SessionManager configWebSessionManager(){
	// DefaultWebSessionManager manager = new DefaultWebSessionManager();
	// // 加入缓存管理器
	// //manager.setCacheManager(cacheManager);
	// //manager.setSessionDAO(sessionDao);
	// // 删除过期的session
	// manager.setDeleteInvalidSessions(true);
	// // 设置全局session超时时间
	// manager.setGlobalSessionTimeout(1800000);
	// // 是否定时检查session
	// manager.setSessionValidationSchedulerEnabled(true);
	// manager.setSessionIdCookie(simpleCookie());
	// return manager;
	// }

	// /**
	// * 注入cookie模板
	// * @return
	// */
	// @Bean
	// public SimpleCookie simpleCookie(){
	// SimpleCookie simpleCookie = new SimpleCookie("sid-shrio");
	// simpleCookie.setMaxAge(-1);
	// simpleCookie.setHttpOnly(true);
	// return simpleCookie;
	// }
}
