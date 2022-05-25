package com.springboot.study.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupRequestDto {
	
	@NotBlank(message="이메일 란이 비었습니다") // Spring Boot Starter Validation라이브러리 기능으로, Null, 빈 문자열, 스페이스만 있는 문자열 불가,
	@Email(message="이메일 형식을 확인해 주세요")
	private String email;
	@NotBlank
	private String name;
	@NotBlank
	private String username;
	@NotBlank
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W).{8,20}",message="(8~20자까지) 비밀번호는 영문 대소문자와 숫자, 특수기호가 적어도 1개 이상 포함되어야 합니다.")
	private String password;

}
