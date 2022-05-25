package com.springboot.study.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.study.domain.user.User;
import com.springboot.study.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 로그인 요청시 자동요청
		
		User userEntity = userRepository.findUserByUsername(username);
		System.out.println(userEntity);
		System.out.println("로그인 요청");
		
		if(userEntity == null) {
			return null; // uri는 auth/signin?error
		}
		
		return new PrincipalDetails(userEntity); // Authentication 세션 저장 역할
		
	}

}
