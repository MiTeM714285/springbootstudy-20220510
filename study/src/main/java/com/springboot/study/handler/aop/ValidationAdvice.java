package com.springboot.study.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.springboot.study.handler.ex.CustomValidationApiException;


/*
	Advice : 공통 기능을 담은 구현체로, Advice는 Aspect가 무엇을 언제 적용할 지를 정의하는 것.
	
	Aspect : 여러 핵심 기능에 적용될 관심사 모듈을 의미하며, Aspect에는 구체적인 기능을 구현한 Advice와 Advice가 어디에서 적용될지 결정하는 PointCut(메소드 위치 정보)을 포함하고 있다.
	
	PointCut : 공통 기능이 적용될 대상을 결정하는 것.
	
	Target : 공통 기능을 부여할 대상으로, 핵심 기능을 담당하는 비즈니스 로직이며, 어떤 관심사들과도 관계를 맺지 않는다.
	
	JoinPoint : Advice가 적용될 지점을 의미. Spring에서는 메소드에만 제공이 된다.
 */

@Aspect
@Component
public class ValidationAdvice {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ValidationAdvice.class);

	@Around("execution(* com.springboot.study.web.api.*Controller.*(..))") // 모든 Controller를 포함한 클래스에 접근. *(..)는 매개변수를 0개 이상(..) 받는 모든 메소드(*)에 접근한다는 의미
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable { // Throwable : exception의 최상위, proceedingJoinPoint는 해당 메소드 자체를 의미
		
		Object[] args = proceedingJoinPoint.getArgs(); // 가져온 메소드의 매개변수를 가져오기
		for(Object arg : args) {
			if(arg instanceof BindingResult) { // arg의 타입이 BindingResult라면
				BindingResult bindingResult = (BindingResult) arg; // BindingResult로 다운 캐스팅
				if (bindingResult.hasErrors() == true) {
					// pring Boot Starter Validation을 거쳐 에러가 하나라도 있다면
					Map<String, String> errorMap = new HashMap<String, String>();
					for (FieldError error : bindingResult.getFieldErrors()) { // 변수 에러 하나씩 체크
						errorMap.put(error.getField(), error.getDefaultMessage()); // 필드명을 가져와서 에러메세지를 띄우도록(원하는 메세지 또는 Default 기본메세지)
					}
					LOGGER.info("Validation AOP 실행됨");
					
					throw new CustomValidationApiException("유효성 검사 실패", errorMap); // CustomValidationApiException 메소드 호출로 예외처리 
				}
			}
		} // 여기까지 전처리	
		
		return proceedingJoinPoint.proceed(); // 메소드 실행
	}
}