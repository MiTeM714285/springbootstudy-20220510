package com.springboot.study.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Value("${file.path}")
	private String filePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/image/**")
		.addResourceLocations("file:///" + filePath) // '/image' 경로를 해당 경로로 대체
		// http://localhost:8000/image/profile/파일명 주소입력시 해당 파일을 출력
		.setCachePeriod(60*60) // 캐시 지속시간 설정 (60분). 브라우저 캐시는 웹 브라우저가 접속한 페이지를 캐시하는 것이다. 이를 통해 웹 서버 접속을 줄이고 브라우저 표시를 고속화할 수 있다.
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
	}
}
