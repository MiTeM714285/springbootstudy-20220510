package com.springboot.study.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.domain.user.User;
import com.springboot.study.domain.user.UserRepository;
import com.springboot.study.web.dto.CustomResponseDto;
import com.springboot.study.web.dto.SignupRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder; //SecurityConfig의 BCryptPasswordEncoder
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); // 패스워드 암호화
		// List<String> roles = List.of("ROLE_USER","ROLE_MANAGER","ROLE_ADMIN"); // 리스트에 바로 값 넣기
		List<String> roles = List.of("ROLE_USER"); // 리스트에 바로 값 넣기
		user.setRoles(String.join(",", roles)); // 리스트에 들어있는 문자열들을 쉼표(,)로 구분하여 합치기
		userRepository.insertUser(user);
		return new ResponseEntity<>(new CustomResponseDto<User>(1, "회원가입완료",user), HttpStatus.OK);
		
	}
	
	@PostMapping("/auth/signup")
	public ResponseEntity<?> signup(@Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) { 
		// @Valid : validation 검사를 거치도록, Spring Boot Starter Validation을 거쳐서 나온 결과는 BindingResult에 저장

		System.out.println(signupRequestDto);
		return new ResponseEntity<>(new CustomResponseDto<SignupRequestDto>(1, "회원가입 완료.", signupRequestDto), HttpStatus.OK);
	}

	
	@GetMapping("/auth/signup/check/{username}")
	public ResponseEntity<?> checkUsername(@PathVariable String username) {
		HttpStatus status = null;
		CustomResponseDto<String> customResponseDto = null;
		User user = new User();
		
		// 유효성 검사 문제로 bindingResult 예외사항 발생시 ControllerExceptionHandler의 예외 자동적 처리.

		if (username.equals(user.getUsername())) {
			customResponseDto = new CustomResponseDto<String>(-1, "중복된 아이디입니다.", username);
			status = HttpStatus.BAD_REQUEST;
		} else {
			customResponseDto = new CustomResponseDto<String>(1, "사용 가능한 아이디입니다.", username);
			status = HttpStatus.OK;
		}
		return new ResponseEntity<>(customResponseDto, status);
	}

	@GetMapping("/authentication")
	public ResponseEntity<?> getAuthentication(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println(principalDetails.getUser().getUsercode());
		String password = principalDetails.getUser().getPassword();
		System.out.println(bCryptPasswordEncoder.matches("1234", password));
		return new ResponseEntity<>(new CustomResponseDto<PrincipalDetails>(1, "세션정보",principalDetails), HttpStatus.OK);
	}
	
	/*
	@GetMapping("/user")
	public ResponseEntity<?> testUser() {
		return new ResponseEntity<>(new CustomResponseDto<String>(1,"유저권한","role_user"), HttpStatus.OK);
	}
	*/
	@GetMapping("/manager")
	public ResponseEntity<?> testManager() {
		return new ResponseEntity<>(new CustomResponseDto<String>(1,"매니저권한","role_manager"), HttpStatus.OK);
	}
	@GetMapping("/admin")
	public ResponseEntity<?> testAdmin() {
		return new ResponseEntity<>(new CustomResponseDto<String>(1,"관리자권한","role_admin"), HttpStatus.OK);
	}
}
