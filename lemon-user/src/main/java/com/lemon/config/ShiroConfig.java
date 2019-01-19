package com.lemon.config;

import com.lemon.relam.LoginRelam;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author shijianpeng
 */
@Configuration
public class ShiroConfig {

  /**
   * 配置shiro filter
   *
   * @return ShiroFilterFactoryBean
   */
  @Bean
  public ShiroFilterFactoryBean shirFilter() {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager());
    shiroFilterFactoryBean.setLoginUrl("/u/index");
    shiroFilterFactoryBean.setSuccessUrl("/u/main");
    shiroFilterFactoryBean.setUnauthorizedUrl("/403");
    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
      filterChainDefinitionMap.put("/u/video/get", "anon");
      filterChainDefinitionMap.put("/static/**", "anon");
      filterChainDefinitionMap.put("/u/user/login", "anon");
      filterChainDefinitionMap.put("/u/user/logout", "logout");

    filterChainDefinitionMap.put("/**", "authc");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
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
    securityManager.setRealm(loginRealm());
    return securityManager;
  }

  /**
   * 配置shiroRelam并指定凭证匹配器
   * 
   * @return LoginRelam
   */
  @Bean
  public LoginRelam loginRealm() {
    LoginRelam loginRelam = new LoginRelam();
    loginRelam.setCredentialsMatcher(hashedCredentialsMatcher());
    return loginRelam;
  }

  /**
   * 凭证匹配器
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
    /// proxyCreator.setProxyTargetClass(true);
    return proxyCreator;
  }

  /**
   * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
   */
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager());
    return advisor;
  }
}
