package com.springboot.study.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SigninRequestDto {
	
	@NotBlank(message="아이디 입력란이 비었습니다")
	private String username;
	@NotBlank(message="비밀번호 입력란이 비었습니다")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W).{8,20}",message="(8~20자까지) 비밀번호는 영문 대소문자와 숫자, 특수기호가 적어도 1개 이상 포함되어야 합니다.")
	private String password;

}
