package com.springboot.study.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.study.filter.TestFilter1;
import com.springboot.study.filter.TestFilter2;

@Configuration
public class FilterConfig { // 요청전 및 요청 후 처리
	
	@Bean
	public FilterRegistrationBean<TestFilter1> filter1() {
		FilterRegistrationBean<TestFilter1> bean = new FilterRegistrationBean<TestFilter1>(new TestFilter1());
		bean.addUrlPatterns("/*");
		bean.setOrder(1);
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<TestFilter2> filte2() {
		FilterRegistrationBean<TestFilter2> bean = new FilterRegistrationBean<TestFilter2>(new TestFilter2());
		bean.addUrlPatterns("/*");
		bean.setOrder(0);
		return bean;
	}
}
