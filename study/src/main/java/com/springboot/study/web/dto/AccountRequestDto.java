package com.springboot.study.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountRequestDto {
	@NotBlank(message="이메일 란이 비었습니다") // Spring Boot Starter Validation라이브러리 기능으로, Null, 빈 문자열, 스페이스만 있는 문자열 불가,
	@Email(message="이메일 형식을 확인해 주세요")
	private String email;
	@NotBlank(message="이름 란이 비었습니다")
	private String name;
}
