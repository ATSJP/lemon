package com.lemon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author sjp
 * @date 2019/1/19
 **/
@Configuration
public class UrlRewriteFilterConfig extends UrlRewriteFilter {

	@Value("classpath:/urlRewrite/urlRewrite.xml")
	private Resource resource;

	@Override
	protected void loadUrlRewriter(FilterConfig filterConfig) throws ServletException {
		try {
			Conf conf = new Conf(filterConfig.getServletContext(), resource.getInputStream(), resource.getFilename(),
					"lemon-pay");
			checkConf(conf);
		} catch (IOException ex) {
			throw new ServletException(
					"Unable to load URL rewrite configuration file from classpath:/urlRewrite/urlRewrite.xml", ex);
		}
	}

}