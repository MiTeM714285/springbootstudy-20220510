package com.springboot.study.web.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.study.config.auth.PrincipalDetails;
import com.springboot.study.service.user.AccountService;
import com.springboot.study.web.api.data.User;
import com.springboot.study.web.dto.AccountRequestDto;
import com.springboot.study.web.dto.CustomResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

	/*
	 * 1. 사용자이름 중복확인(/auth/signup/check/{username}) -> User객체에 존재하는 사용자이름과 같으면 중복됨을
	 * 알려줌, 아닐시 사용가능(200)을 알려줌 2. 회원가입(/auth/signup) -> 회원가입 정보출력(console로출력) 및 회원가입
	 * 완료 3. 로그인(/auth/signin) -> User개체와 정보 일치시 로그인성공, 아닐시 로그인실패 4. 회원정보
	 * 수정(/account/{username}) -> name과 email 임의값으로 수정 후 회원수정 완료 및 실패 5.
	 * 회원탈퇴(/account/{username}) -> 회원탈퇴 완료 및 실패
	 */
	
	private final AccountService accountService;

	/*
	@PostMapping("/auth/signin")
	public ResponseEntity<?> signin(@Valid SigninRequestDto signinRequestDto, BindingResult bindingResult) {


		User user = new User();

		if (!signinRequestDto.getUsername().equals(user.getUsername())) {
			return new ResponseEntity<>(new CustomResponseDto<SigninRequestDto>(-1, "잘못된 아이디", signinRequestDto), HttpStatus.BAD_REQUEST);
		} else {
			if (!signinRequestDto.getPassword().equals(user.getPassword())) {
				return new ResponseEntity<>(new CustomResponseDto<SigninRequestDto>(-1, "잘못된 패스워드", signinRequestDto), HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(new CustomResponseDto<User>(1, "로그인 성공", user), HttpStatus.OK);
			}
		}
	}
	*/
	
	// 로그인 관련 요청은 PrincipalDetailsService.java의 loadUserByUsername에

	@PutMapping("/account/{username}")
	public ResponseEntity<?> updateMember(@PathVariable String username, @Valid AccountRequestDto accountRequestDto, BindingResult bindingResult) {

		User user = new User();
		if (!username.equals(user.getUsername())) {
			return new ResponseEntity<>(new CustomResponseDto<String>(-1, "해당 아이디가 존재하지 않습니다.", username), HttpStatus.BAD_REQUEST);
		} else {
			user.setEmail(accountRequestDto.getEmail());
			user.setName(accountRequestDto.getName());
			return new ResponseEntity<>(new CustomResponseDto<User>(1, "회원정보 변경완료", user), HttpStatus.OK);
		}
	}

	@DeleteMapping("/account/{username}")
	public ResponseEntity<?> deleteMember(@PathVariable String username) {
		User user = new User();
		if (!username.equals(user.getUsername())) {
			return new ResponseEntity<>(new CustomResponseDto<String>(-1, "해당 아이디가 존재하지 않습니다.", username), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(new CustomResponseDto<String>(1, "회원탈퇴 완료.", username), HttpStatus.OK);
		}
	}
	
	
	@PutMapping("/account/profile/img")
	public ResponseEntity<?> updateProfileImg(@RequestPart MultipartFile file, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {
		boolean result = accountService.updateProfileImg(file, principalDetails);
		if (result) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/account/profile/")
	public ResponseEntity<?> updateProfile(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
