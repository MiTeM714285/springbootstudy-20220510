/*
	ClassName : CustomResponseDto
	Version Information : 1.0
	Copyright Notice : junil(2022.05.11 ~ 2027.05.11)
 */

// 패키지 정보
package com.springboot.study.web.dto;

// import 정보
import java.util.Scanner; // import 이유

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class description(클래스 정보)
 * @author mitem714285
 * @version 1.0
 * 
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomResponseDto<T> {
	
	/*
	 *  1 또는 -1를 반환
	 *  1 : 성공, -1 : 실패
	 */
	private int code;

	/*
	 * 응답 메세지 내용
	 */
	private String msg;
	
	/*
	 * 응답 데이터
	 */
	private T data;
	
}
